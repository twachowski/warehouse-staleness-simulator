package pl.polsl.zbdihd.wss.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyRate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "CURRENCY")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CurrencyEntity implements Versionable {

    @Id
    @Column(nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyCode code;

    @Column(nullable = false,
            precision = 10,
            scale = 3)
    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDateTime versionDateTime;

    @Override
    public LocalDateTime versionDateTime() {
        return versionDateTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof CurrencyEntity that) {
            return this.code == that.code;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "CurrencyEntity{" +
                "code=" + code +
                ", rate=" + rate +
                ", versionDateTime=" + versionDateTime +
                '}';
    }

    public CurrencyEntity withRateChange(final CurrencyRate currencyRate) {
        this.rate = rate.add(currencyRate.rateChange());
        this.versionDateTime = currencyRate.versionDateTime();
        return this;
    }

}
