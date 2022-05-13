package pl.polsl.zbdihd.wss.persistence.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.polsl.zbdihd.wss.domain.Versionable;
import pl.polsl.zbdihd.wss.domain.currency.CurrencyCode;
import pl.polsl.zbdihd.wss.domain.transaction.Transaction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionEntity implements Versionable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "transaction_sequence_generator")
    @SequenceGenerator(name = "transaction_sequence_generator",
                       sequenceName = "transaction_sequence")
    private Long id;

    @Column(nullable = false, length = 3)
    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @Column(nullable = false,
            precision = 12,
            scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime creationDateTime;

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
        if (o instanceof TransactionEntity that) {
            if (this.id == null && that.id == null) {
                return false;
            }
            return Objects.equals(this.id, that.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", currencyCode=" + currencyCode +
                ", amount=" + amount +
                ", creationDateTime=" + creationDateTime +
                ", versionDateTime=" + versionDateTime +
                '}';
    }

    public static TransactionEntity valueOf(final Transaction transaction) {
        return new TransactionEntity(null,
                                     transaction.currencyCode(),
                                     transaction.amount(),
                                     transaction.creationDateTime(),
                                     transaction.versionDateTime());
    }

}
