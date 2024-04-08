package WebCinema.Services.Implements;

import WebCinema.Config.VNPayConfig;
import WebCinema.Repository.BillRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VNPayService {
    private final EmailService emailService;
    private final BillRepository billRepository;

    public String createOrder(String code, String urlReturn) {
        var currentBill = billRepository.findByTradingCode(code)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String bankCode = "NCB";
        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;
        String orderType = "order-type";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf((int) currentBill.getTotalMoney() * 100));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode); // Remove to choose another payment method
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
//        vnp_Params.put("vnp_OrderInfo", "Code orders:" + vnp_TxnRef); // Maybe another name
        vnp_Params.put("vnp_OrderInfo", currentBill.getName() + " with code " + currentBill.getTradingCode());
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");

        vnp_Params.put("vnp_ReturnUrl", urlReturn + VNPayConfig.vnp_Returnurl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                try {
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.vnp_HashSecret, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return VNPayConfig.vnp_PayUrl + "?" + queryUrl;
    }

    // response to email
    public int orderReturn(HttpServletRequest request) {
        Map<String, String> fields = new HashMap<>();
        for (Enumeration<String> params = request.getParameterNames(); params.hasMoreElements(); ) {
            String fieldName = null;
            String fieldValue = null;
            try {
                fieldName = URLEncoder.encode(params.nextElement(), StandardCharsets.US_ASCII.toString());
                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                fields.put(fieldName, fieldValue);
            }
        }

        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
        fields.remove("vnp_SecureHashType");
        fields.remove("vnp_SecureHash");
        String signValue = VNPayConfig.hashAllFields(fields);
        if (signValue.equals(vnp_SecureHash)) {
            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {

                String orderInfo = request.getParameter("vnp_OrderInfo");
                String paymentTime = request.getParameter("vnp_PayDate");
                String transactionId = request.getParameter("vnp_TransactionNo");
                String totalPrice = request.getParameter("vnp_Amount");

                // Extracting attributes from the model and formatting them into a string
                StringBuilder message = new StringBuilder();
                message.append("Order Info: ").append(orderInfo).append(", ");

                // Create a SimpleDateFormat object with the input format
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                // Create a SimpleDateFormat object with the desired output format
                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");

                try {
                    // Parse the input string to obtain a Date object
                    Date date = inputFormat.parse(paymentTime);

                    // Format the Date object to the desired output format
                    String formattedDate = outputFormat.format(date);

                    message.append("Payment Time: ").append(formattedDate).append(", ");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                message.append("Transaction ID: ").append(transactionId).append(", ");

                // Dividing the totalPrice by 100
                double totalPriceValue = Double.parseDouble(totalPrice); // Parse the String to a double
                double totalPriceDivided = totalPriceValue / 100; // Divide by 100

                // Formatting totalPriceDivided to add separators for thousands and drop trailing zeroes
                DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                String formattedTotalPrice = decimalFormat.format(totalPriceDivided);

                message.append("Total Price: ").append(formattedTotalPrice).append(".");

                // Send the response via email
                sendResponse(message);

                return 1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    //send mail new Password
    private void sendResponse(StringBuilder detail) {
        String subject = "VNPay payment Response";
        String body = "Data response: " + detail;
        // Sending the email
        emailService.sendEmail("tuanhd131@gmail.com", subject, body);
    }

    // basic return order
//    public int orderReturn(HttpServletRequest request) {
//        Map fields = new HashMap();
//        for (Enumeration params = request.getParameterNames(); params.hasMoreElements(); ) {
//            String fieldName = null;
//            String fieldValue = null;
//            try {
//                fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
//                fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            if ((fieldValue != null) && (fieldValue.length() > 0)) {
//                fields.put(fieldName, fieldValue);
//            }
//        }
//
//        String vnp_SecureHash = request.getParameter("vnp_SecureHash");
//        if (fields.containsKey("vnp_SecureHashType")) {
//            fields.remove("vnp_SecureHashType");
//        }
//        if (fields.containsKey("vnp_SecureHash")) {
//            fields.remove("vnp_SecureHash");
//        }
//        String signValue = VNPayConfig.hashAllFields(fields);
//        if (signValue.equals(vnp_SecureHash)) {
//            if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
//
//                return 1;
//            } else {
//                return 0;
//            }
//        } else {
//            return -1;
//        }
//    }

}
