package account.model;

import account.dto.types.CurrentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account_current")
@Entity
public class AccountCurrent {

    @Id
    @NotNull

    private String iban;
    private Double balance;
    private Integer individualId;
    private LocalDateTime startDate;
    private CurrentStatus currentStatus;
    private boolean primaryAccount;
    //private IndividualDTO individualDTO;
}
