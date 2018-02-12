package com.rdebokx.formica.execution;

import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.core.Colony;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.stream.Collectors;

public class ResultPrinter {

  /**
   * Helper function for printing the current Buckets contents of a given Colony to a particular writer.
   * @param writer The writer to write a String representation of the current Buckets contents too, e.g. a FileWriter or StringWriter.
   * @param colony The colony of which the Buckets contents need to be printed.
   * @throws IOException
   */
  public static void printBucketResults(Writer writer, Colony colony) throws IOException {
    List<Bucket> buckets = colony.getBucketsCopy();

    int bucketCounter = 0;
    for(int i = 0; i < buckets.size(); i++){
      Bucket bucket = buckets.get(i);
      if(!bucket.isEmpty()){
        writer.write("Bucket#" + bucketCounter + ":\n");
        writer.write("\tcentroid:\n\t\t" + bucket.getCentroid() + "\n");
        writer.write("\tcontents:\n");
        List<String> dataPointStrings = (List<String>) bucket.stream().map(e -> "\t\t" + e.toString()).collect(Collectors.toList());
        writer.write(String.join("\n", dataPointStrings) + "\n");

        bucketCounter++;
      }
    }
  }
}