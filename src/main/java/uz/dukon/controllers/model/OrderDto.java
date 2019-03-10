package uz.dukon.controllers.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 17.02.2019.
 */
public class OrderDto {
    private Long id;
    private BigDecimal totalAmount;
    private BigDecimal pcardSuma = BigDecimal.ZERO;
    private BigDecimal cashSum = BigDecimal.ZERO;
    private List<TransactionDto> list = new ArrayList<>();

    public List<TransactionDto> getList() {
        return list;
    }

    public void setList(List<TransactionDto> list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getPcardSuma() {
        return pcardSuma;
    }

    public void setPcardSuma(BigDecimal pcardSuma) {
        this.pcardSuma = pcardSuma;
    }

    public BigDecimal getCashSum() {
        return cashSum;
    }

    public void setCashSum(BigDecimal cashSum) {
        this.cashSum = cashSum;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", totalAmount=" + totalAmount +
                ", pcardSuma=" + pcardSuma +
                ", cashSum=" + cashSum +
                ", list=" + list +
                '}';
    }
}
