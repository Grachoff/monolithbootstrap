package com.altarix.entities.files;

import com.altarix.entities.security.User;
import com.altarix.models.file.FileState;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Builder
public class File {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 1024)
    private String fileName;
    @Column(length = 16)
    private String mimeType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploaded;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted;

    @Column(length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private FileState fileState;

    @OneToOne(optional = false)
    @NotNull
    private User user;
}
