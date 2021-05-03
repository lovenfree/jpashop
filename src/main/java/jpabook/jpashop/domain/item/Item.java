package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //ㅂㅣ지니스 로직 재고 수량 증가 : 데이터를 가지고 있는 쪽에 관리하기 좋다
   public void addStock(int quantity){
        this.stockQuantity += quantity;
    }


    //재고 수량 감소
    public void removeStock(int quantity){
       int restStock = this.stockQuantity - quantity;
       if(restStock < 0){
           throw new NotEnoughStockException("need more stock");
       }
       this.stockQuantity = restStock;
    }
}
