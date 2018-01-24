package com.altarix.entities.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 1024)
    @NotNull
    @Size(max = 1024)
    private String log;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    public static DbLog log(String log) {
        DbLog dbLog = new DbLog();
        dbLog.setDate(new Date());
        dbLog.setLog(log);
        return dbLog;
    }
}
