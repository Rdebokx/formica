package com.rdebokx.formica.core;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Bucket Class containing a collection of DataPoints.
 * This is basically a wrapper around an ArrayList, providing some convenience methods and attributes like the centroid of the bucket.
 * Upon each mutation of this Bucket (addition or removal), the centroid will be recalculated which has a complexity of O(n).
 * @param <E> The type of DataPoints this bucket holds.
 */
public class Bucket<E extends DataPoint<?>> implements Iterable<E> {

  /**
   * ArrayList to store the DataPoints in this bucket.
   */
  private final ArrayList<E> list;

  /**
   * The centroid of this bucket, being the DataPoint in this bucket of which the average distance to all other
   * DataPoints is smaller than that of any other DataPoint in this bucket.
   */
  private DataPoint centroid;

  /**
   * The colony that uses this bucket. Used for its DistanceMetric.
   */
  private final Colony colony;

  /**
   * Constructor, constructing an empty Bucket for the given colony.
   * @param colony The colony that uses this bucket.
   */
  public Bucket(Colony colony){
    this.colony = colony;
    list = new ArrayList<>();
  }

  /**
   * Constructor, constructing a bucket for the given colony contaiing the provided DataPoints.
   * @param colony The colony that uses this bucket.
   * @param dataPoints The DataPoints to initialize this bucket with.
   */
  public Bucket(Colony colony, Collection<E> dataPoints){
    this(colony);
    list.addAll(dataPoints);
    calculateCentroid();
  }

  /**
   * Constructor, constructing a Bucket for the given colony containing the provided DataPoints.
   * @param colony The colony that uses this bucket.
   * @param dataPoints The DataPoints to initialize this Bucket with.
   */
  public Bucket(Colony colony, E ... dataPoints){
    this(colony, Arrays.asList(dataPoints));
  }

  /**
   * Add a new DataPoint to this Bucket.
   * After adding the provided DataPoint, the centroid will be recalculated.
   * @param dataPoint The DataPoint to be added
   * @return true iff the DataPoint was successfully added.
   */
  public boolean add(E dataPoint){
    boolean result = list.add(dataPoint);
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

  /**
   * Remove the DataPoint positioned at the given index.
   * After this DataPoint was removed, the centroid of the bucket is recalculated.
   * @param index The index from which to remove a DataPoint
   * @return The removed DataPoint.
   */
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

  /**
   * Check whether this bucket contains the given element.
   * @param element The element to be looked for.
   * @return true iff this bucket contained the given element.
   */
  public boolean contains(E element){
    return list.contains(element);
  }

  /**
   * Helper method for determining the centroid of the bucket.
   * This is done by iterating over all DataPoint currently in the bucket and replacing the centroid iff a DataPoint
   * has a lower average distance to all other DataPoints in the bucket than the current centroid.
   * Iff the bucket is empty, the centroid will be set to null.
   *
   * NOTE: This operation has a straight-forward implementation of complexity O(n) in order to support DataPoints of
   * any type. If the context-specif DataPoints you are using allow you to recalculate the centroid in a more efficient
   * way, it is advised to extend this class for your type of DataPoints and override this method.
   */
  protected void calculateCentroid() {
    if(this.isEmpty()){
      centroid = null;
    } else {
      double minDistanceToBucket = centroid.avgDistanceToBucket(this, colony.distanceCalculator);
      for (DataPoint dataPoint : list) {
        double distance = dataPoint.avgDistanceToBucket(this, colony.distanceCalculator);
        if (distance < minDistanceToBucket) {
          minDistanceToBucket = distance;
          centroid = dataPoint;
        }
      }
    }
  }

  /**
   * @return The centroid of this bucket, being the DataPoint that is the median of all DataPoints in this bucket.
   */
  public DataPoint getCentroid() {
    return this.centroid;
  }

  /**
   * Return a copy of the current bucket. Used for calculating quality scores ed while safeguarding the data integrity
   * in the Colony.
   * @return Copied bucket containing the same contents.
   */
  public Bucket copy(){
    return new Bucket(colony, list);
  }
}
