package com.altarix.entities.files;

import com.altarix.utility.RandomHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static com.altarix.ConstantsHolder.FILE_TOKEN_LENGTH;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkToFileForDownload {
    @Column(length = FILE_TOKEN_LENGTH)
    @Id
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    private File file;

    @Temporal(TemporalType.TIMESTAMP)
    private Date whenQueried;
    @Temporal(TemporalType.TIMESTAMP)
    private Date untilAlive;

    public static LinkToFileForDownload createLink(long id) {
        LinkToFileForDownload linkToFileForDownload = new LinkToFileForDownload();
        linkToFileForDownload.file = new File();
        linkToFileForDownload.getFile().setId(id);
        linkToFileForDownload.setWhenQueried(new Date());
        linkToFileForDownload.setUntilAlive(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)));
        linkToFileForDownload.setLink(RandomHelper.createRandomString(FILE_TOKEN_LENGTH));

        return linkToFileForDownload;
    }


}
