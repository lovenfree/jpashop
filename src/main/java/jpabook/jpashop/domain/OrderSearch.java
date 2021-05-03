package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    OrderStatus orderStatus;
    String memberName;
}
