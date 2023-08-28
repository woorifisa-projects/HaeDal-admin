package dev.syntax.backend.product.dto.response;

import dev.syntax.backend.product.model.Product;
import dev.syntax.backend.product.model.Tag;
import dev.syntax.backend.subscribe.model.Subscribe;
import dev.syntax.backend.user.model.ServicePurpose;
import dev.syntax.backend.user.model.UserAgeGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 상품 리스트에서 보여 줄 상품정보들
@AllArgsConstructor
@Getter
@Builder
public class ProductResponse {


    private final Long productId; // 상품 id
    private final Long productAsset; // 추천을 위한 상품 자산
    private ServicePurpose servicePurpose; // 서비스 이용 목적
    private UserAgeGroup userAgeGroup; // 연령대
    private Tag tag;// 어떤 태그를 가지고 있는지
    private final String productName; // 상품 이름
    private final String shortInfo; // 상품 한 줄 설명
    private String longInfo; // 상품 전체 설명
    private final int period; // 저축 기간
    private int requiredStartMoney; // 시작 금액
    private final double interestRate; // 금리
    private List<Subscribe> subscribers; // 한 개의 상품에 몇 명의 고객들이 가입했는지 list
    private boolean isDeposit; // 예금, 적금 타입 확인용 컬럼(0=예금, 1=적금)
    private int subscription; // 월 납입금액(구독료)


    public static ProductResponse from(Product product)  {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productAsset(product.getMaxProductMoney())
                .tag(product.getTag())
                .productName(product.getProductName())
                .shortInfo(product.getShortInfo())
                .period(product.getPeriod())
                .interestRate(product.getInterestRate())
                .isDeposit(product.isDeposit()) // boolean타입일 때는 Getter는 is로 시작하는 것이 관례
                .subscription(product.getSubscription())
                .build();
    }
}
