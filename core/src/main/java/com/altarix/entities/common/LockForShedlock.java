package com.altarix.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "LOСK4SHEDLOCK")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockForShedlock {

    @Column(length = 64)
    @Id
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lockUntil;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockedAt;

    @Size(max = 255)
    private String lockedBy;
}
