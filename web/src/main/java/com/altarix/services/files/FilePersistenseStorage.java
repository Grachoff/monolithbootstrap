package com.altarix.services.files;

import com.altarix.entities.files.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface FilePersistenseStorage {
    CompletableFuture<File> saveFile(File file, byte[] filedata);
    InputStream readFile(String filename) throws IOException;
    void deleteFile(String filename) throws IOException;
}
