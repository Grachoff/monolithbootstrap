package com.altarix.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockForShadlock {

    @Column(length = 64)
    @Id
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lockUntil;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockedAt;
}
