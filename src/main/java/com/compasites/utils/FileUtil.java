package com.compasites.utils;

import com.compasites.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Sobhan Babu on 27-04-2016.
 */
public class FileUtil {
    static Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    public static void moveInputFile(String inputFileDir, String outputFileDir, String fileName) {
        File filetoMove = new File(inputFileDir);
        String fileNameParts[] = fileName.split("\\.");
        filetoMove.renameTo(new File(outputFileDir + fileNameParts[0] +
                "_" + System.currentTimeMillis() + "." + fileNameParts[1]));

    }

    public static void moveInputFile(String inputFileDir, String outputFileDir, String fileName, String time) {
        File filetoMove = new File(inputFileDir);
        String fileNameParts[] = fileName.split("\\.");
        filetoMove.renameTo(new File(outputFileDir + fileNameParts[0] +
                "_" + time + "." + fileNameParts[1]));

    }

    public static void deleteOutputFiles(String dir) {
        //to delete the generated output files
        File file = new File(dir);
        File[] files = file.listFiles();
        for (File f:files) {
            if (f.isFile() && f.exists()) {
                f.delete();
            }
        }
    }

    public static void deleteFile(String fileName){
        File file = new File(fileName);
        if (file.isFile() && file.exists() && file.length() == 0) {
            file.delete();
        }
    }

    public static void deleteFileNoLenCheck(String fileName){
        File file = new File(fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }

    public static void renameFile(String fromFileName, String toFileName){
        File filetoRename = new File(fromFileName);
        filetoRename.renameTo(new File(toFileName));
    }

    public static void createZip(String zipFile, String[] sourceFiles, String[] destFiles) throws IOException {

        //create byte buffer
        byte[] buffer = new byte[1024];

        //create object of FileOutputStream
        FileOutputStream fout = new FileOutputStream(zipFile);

        //create object of ZipOutputStream from FileOutputStream
        ZipOutputStream zout = new ZipOutputStream(fout);
        int srcfilesLength = sourceFiles.length;
        for (int i = 0; i < srcfilesLength; i++) {
            //create object of FileInputStream for source file
            FileInputStream fin = new FileInputStream(sourceFiles[i]);

            /*
             * To begin writing ZipEntry in the zip file, use
             *
             * void putNextEntry(ZipEntry entry)
             * method of ZipOutputStream class.
             *
             * This method begins writing a new Zip entry to
             * the zip file and positions the stream to the start
             * of the entry data.
             */
            zout.putNextEntry(new ZipEntry(destFiles[i]));

            /*
             * After creating entry in the zip file, actually
             * write the file.
             */
            int length;

            while ((length = fin.read(buffer)) > 0) {
                zout.write(buffer, 0, length);
            }

            /*
             * After writing the file to ZipOutputStream, use
             *
             * void closeEntry() method of ZipOutputStream class to
             * close the current entry and position the stream to
             * write the next entry.
             */
            zout.closeEntry();

            //close the InputStream
            fin.close();
        }

        //close the ZipOutputStream
        zout.close();
    }

    public static byte[] getByteArray(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream is = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte data[] = new byte[16384];
        try{
            is = new FileInputStream(file);
            while((nRead = is.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            buffer.flush();

            is.close();
        }

        return buffer.toByteArray();
    }

    public static String reverseFileReader (String filename) {
        RandomAccessFile randomfile = null;
        long position = -1;
        String lastLine = null;
        try {
            randomfile = new RandomAccessFile(filename, "r");
            // Set our seek position to the end of the file
            position = randomfile.length();

            // Seek to the end of the file
            randomfile.seek(position);
            //Move our pointer to the first valid position at the end of the file.
            String thisLine = randomfile.readLine();
            while (thisLine == null) {
                position--;
                randomfile.seek(position);
                thisLine = randomfile.readLine();
                randomfile.seek(position);
            }

            lastLine = readLine(position, randomfile);
        }catch (FileNotFoundException fe){
            LOG.error("FileNotFoundException : ", fe);
        }catch (IOException ioe){
            LOG.error("IOException : ", ioe);
        }

        return lastLine;
    }

    public static String readLine(long position, RandomAccessFile randomfile) throws IOException {
        int thisCode;
        char thisChar;
        String finalLine="";

        // If our position is less than zero already, we are at the beginning
        // with nothing to return.
        if ( position < 0 ) {
            return null;
        }

        for(;;) {
            // we've reached the beginning of the file
            if ( position < 0 ) {
                break;
            }
            // Seek to the current position
            randomfile.seek(position);

            // Read the data at this position
            thisCode=randomfile.readByte();
            thisChar=(char)thisCode;

            // If this is a line break or carrige return, stop looking
            if (thisCode == 13 || thisCode == 10 ) {
                // See if the previous character is also a line break character.
                // this accounts for crlf combinations
                randomfile.seek(position-1);
                int nextCode=randomfile.readByte();
                if ( (thisCode == 10 && nextCode == 13) || (thisCode == 13 && nextCode == 10) ) {
                    // If we found another linebreak character, ignore it
                    position=position-1;
                }
                // Move the pointer for the next readline
                position--;
                break;
            } else {
                // This is a valid character append to the string
                finalLine=thisChar + finalLine;
            }
            // Move to the next char
            position--;
        }

        // return the line
        return finalLine;
    }

    public static String lastModifiedFile(File dirPath, String ext) {
        String fileName = null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.FILE_NAME_DATEFORMAT);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File dirPath) {
                return dirPath.getName().endsWith(ext);
            }
        };
        File[] files = dirPath.listFiles(filter);
        File latestModifiedFile = null;

        if (files != null && files.length >= 1){
            for(int i = 0,j = 1;i < files.length; i++ , j++) {
                int  fileNamesDates;
                try {
                    Date date1 = formatter.parse(getFileNames(files[i].getName()));
                    if (latestModifiedFile == null){
                        latestModifiedFile = files[i];
                    }
                    if (j < files.length) {
                        Date date2 = formatter.parse(getFileNames(files[j].getName()));
                        fileNamesDates = date1.compareTo(date2);

                        if (fileNamesDates > 0) {
                            latestModifiedFile = files[i];
                        } else {
                            latestModifiedFile = files[j];
                        }
                    }
                } catch (ParseException e) {}
            }
        }

        if (latestModifiedFile != null){
            fileName = latestModifiedFile.getName();
        }

        return fileName;
    }


    private static String getFileNames(String fileName) {
        String fileNames = Constants.EMTPY;
        if (fileName.length() >= 12){
            String dateVal = fileName.substring(0, fileName.indexOf('.')).substring(fileName.length() - 12);
            boolean isNumber = true;
            try {
                Integer.parseInt(dateVal);
            }catch (NumberFormatException nfe){
                isNumber = false;
            }
            if (isNumber) {
                fileNames = dateVal;
            }
        }

        return fileNames;
    }
}
