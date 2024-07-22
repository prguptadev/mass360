package com.ibm

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord

import java.nio.file.DirectoryStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

static void main(String[] args) {
    println "started"
    Map<String, List<String>> result = [:]

    try {
      String dirPath = "D:\\ss"
        Path dir = Paths.get(dirPath)
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.csv")

        stream.each { Path p ->
            try {
                BufferedReader br = new BufferedReader(new FileReader(p.toFile()))
                CSVParser parser = CSVFormat.DEFAULT.withDelimiter(',' as char).withHeader().parse(br)
                parser.records.each { CSVRecord record ->
                    String cve = record.get("cve")
                    String image = record.get("image")
                    if (result.containsKey(cve)) {
                        result[cve].add(image)
                    } else {
                        result[cve] = [image]
                    }
                }
            } catch (Exception e) {
                println(e)
            }
        }
    } catch (Exception e) {
        println(e)
    }


    String outputPath = "D:\\ss\\finalDataCSV.csv"
    List<String> headers = ["cve", "images"]
    Path outputPath1 = Paths.get(outputPath)

    try (BufferedWriter writer = Files.newBufferedWriter(outputPath1)) {
        writer.write(headers.join(','))
        writer.newLine()

        result.each { cve, images ->
            writer.write("${cve},${images.join('|')}")
            writer.newLine()
        }
        println "file completed"
    } catch (Exception e) {
        e.printStackTrace()
    }
}
