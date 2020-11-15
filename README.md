Formica
=======
Formica is an Ant Colony Optimization Algorithm that allows you to load and model your data to so called DataPoints which will then be sorted by an Artificial Intelligence algorithm, 
based on the Ant Colony Optimization principle. Right now this project is still in an early stage where it is rather a proof of concept that allows you to sort simple DataPoints using
relatively simple Distance Metrics like the Euclidean Distance and Manhattan Distance metric. This should, however, provide you with a backbone for an Ant Colony Optimization Algorithm that is
easy to extend and reuse for any purpose you see fit.

This project is actively maintained and further developed. 
For more information on how to contribute to this project or currently planned development efforts, please see [the contribution instructions](CONTRIBUTING.md).

## Getting started
The Formica module is still to be published to a central repository. Until then one can install the module locally using `mvn install`

Once you loaded Formica into your project, you can load DataPoints into a Colony, provide it with a configuration that suits your needs and start sorting!
An very trivial example of how to run Formica:

```java
import com.rdebokx.formica.core.Colony;
import com.rdebokx.formica.core.DataPoint;
import com.rdebokx.formica.core.Bucket;
import com.rdebokx.formica.execution.Configuration;
import com.rdebokx.formica.metrics.distance.EuclideanMetric;

import java.util.Arrays;
import java.util.List;

public class Example {
  public static void main(String ... args){
    Configuration config = new Configuration(EuclideanMetric.METRIC_NAME, 5, 0.05, 0.25);
    
    Colony colony = new Colony(config, Arrays.asList(
        new DataPoint2D(1.5, 100.8),
        new DataPoint2D(1.3, 101.5),
        new DataPoint2D(1.6, 102.1),
        new DataPoint2D(7.9, 25.8),
        new DataPoint2D(10.15, 30.1)
    ));
    
    long startTime = System.currentTimeMillis();
    while(System.currentTimeMillis() < startTime + 1000*10){
      colony.nextStep();
    }
    List<Bucket> sortedBuckets = colony.getBucketsCopy();
  }
}
```
For more examples of how to use and run Formica, please see the [example package](src/main/java/com/rdebokx/formica/example).  

## Contributions
Contributions of any kind are very much appreciated. For more information on how to contribute to this project or currently planned development efforts, please see [the contribution instructions](CONTRIBUTING.md).  

## Contact
If you'd like to get in touch or if you have any questions regarding the project, please submit an issue through the [Formica Github Repository](https://github.com/Rdebokx/formica/issues).

## License
The Formica software is licensed under the [MIT](LICENSE) license.
