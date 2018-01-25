package com.altarix.entities.files;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkToFileForDownloadTest {
    @Test
    public void createLink() throws Exception {
        LinkToFileForDownload linkToFileForDownload = LinkToFileForDownload.createLink(1);
        Assert.assertEquals(128, linkToFileForDownload.getLink().length());
    }
}