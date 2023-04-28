import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "coupon-client", url = "\${coupon.client-url}")
interface CouponClient
