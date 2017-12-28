package com.rdebokx.formica.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Iterator;

//TODO: javadoc
public class Bucket<E extends DataPoint<?>> implements Iterable<E> {

  private final ArrayList<E> list;

  private DataPoint centroid;

  private final Colony colony;

  public Bucket(Colony colony){
    this.colony = colony;
    list = new ArrayList<>();
  }

  public Bucket(Colony colony, E ... dataPoints){
    this(colony);
    list.addAll(Arrays.asList(dataPoints));
  }

  //TODO: javadoc
  //TODO: test
  public boolean add(E element){
    boolean result = list.add(element);
    calculateCentroid();
    return result;
  }

  @Override
  public boolean equals(Object o){
    boolean result = false;
    if(o instanceof Bucket){
      Bucket that = (Bucket) o;
      result = this.list.equals(that.list) && this.centroid.equals(that.centroid) && this.colony.equals(that.colony);
    }
    return result;
  }

  /**
   * Returns the element in this bucket at the given position.
   * @param index The index of the element to be returned.
   * @return The element at the provided index.
   */
  public E get(int index){
    return list.get(index);
  }

  @Override
  public int hashCode(){
    return Objects.hash(list, centroid, colony);
  }

  /**
   * @return true iff this bucket is empty. False otherwise.
   */
  public boolean isEmpty(){
    return list.isEmpty();
  }

  @Override
  public Iterator<E> iterator() {
    return list.iterator();
  }

  //TODO: javadoc
  //TODO: test
  public E remove(int index){
    E result = list.remove(index);
    calculateCentroid();
    return result;
  }

  /**
   * @return The number of DataPoints in this Bucket.
   */
  public int size() {
    return list.size();
  }

  //TODO: javadoc
  //TODO: monitor if this is not too slow for large datasets
  public void calculateCentroid() {
    double minDistanceToBucket = centroid.avgDistanceToBucket(this, colony.distanceCalculator);
    for(DataPoint dataPoint : list){
      double distance = dataPoint.avgDistanceToBucket(this, colony.distanceCalculator);
      if(distance < minDistanceToBucket){
        minDistanceToBucket = distance;
        centroid = dataPoint;
      }
    }
  }

  /**
   * @return The centroid of this bucket, being the DataPoint that is the median of all DataPoints in this bucket.
   */
  public DataPoint getCentroid() {
    return this.centroid;
  }
}
