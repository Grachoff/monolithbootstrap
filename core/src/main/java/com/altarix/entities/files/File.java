package com.altarix.entities.files;

import com.altarix.entities.security.User;
import com.altarix.models.file.FileState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.InputStream;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", allocationSize = 1)
    private Long id;

    @Column
    private Long size;

    @Column(length = 1024)
    private String fileName;
    @Column(length = 256)
    private String mimeType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploaded;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleted;

    @Column(length = 16)
    @NotNull
    @Enumerated(EnumType.STRING)
    private FileState fileState;

    @ManyToOne()
    @NotNull
    private User user;

    @Transient
    @JsonIgnore
    private InputStream data;
}
