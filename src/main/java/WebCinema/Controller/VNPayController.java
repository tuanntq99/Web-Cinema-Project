package WebCinema.Controller;

import WebCinema.Services.Implements.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
public class VNPayController {
    private final VNPayService vnPayService;

    @PostMapping("/submitOrder/{code}")
    public String submitOrder(@PathVariable("code") String code,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return vnPayService.createOrder(code, baseUrl);
    }

    @GetMapping("/vnPay-payment")
    public String confirmPayment(HttpServletRequest request) {
        return vnPayService.orderReturn(request) == 1 ? "orderSuccess" : "orderFail";
    }
}
