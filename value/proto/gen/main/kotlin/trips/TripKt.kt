//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: trips.proto

package trips;

@kotlin.jvm.JvmSynthetic
public inline fun trip(block: trips.TripKt.Dsl.() -> kotlin.Unit): trips.TripsOuterClass.Trip =
  trips.TripKt.Dsl._create(trips.TripsOuterClass.Trip.newBuilder()).apply { block() }._build()
public object TripKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: trips.TripsOuterClass.Trip.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: trips.TripsOuterClass.Trip.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): trips.TripsOuterClass.Trip = _builder.build()

    /**
     * <pre>
     * The ID of the Locations (pickup and dropoff)
     * </pre>
     *
     * <code>string pickup_location_id = 1;</code>
     */
    public var pickupLocationId: kotlin.String
      @JvmName("getPickupLocationId")
      get() = _builder.getPickupLocationId()
      @JvmName("setPickupLocationId")
      set(value) {
        _builder.setPickupLocationId(value)
      }
    /**
     * <pre>
     * The ID of the Locations (pickup and dropoff)
     * </pre>
     *
     * <code>string pickup_location_id = 1;</code>
     */
    public fun clearPickupLocationId() {
      _builder.clearPickupLocationId()
    }

    /**
     * <code>string dropoff_location_id = 2;</code>
     */
    public var dropoffLocationId: kotlin.String
      @JvmName("getDropoffLocationId")
      get() = _builder.getDropoffLocationId()
      @JvmName("setDropoffLocationId")
      set(value) {
        _builder.setDropoffLocationId(value)
      }
    /**
     * <code>string dropoff_location_id = 2;</code>
     */
    public fun clearDropoffLocationId() {
      _builder.clearDropoffLocationId()
    }

    /**
     * <pre>
     * The datetime of the trip (pickup and dropoff)
     * </pre>
     *
     * <code>.google.protobuf.Timestamp pickup_datetime = 3;</code>
     */
    public var pickupDatetime: com.google.protobuf.Timestamp
      @JvmName("getPickupDatetime")
      get() = _builder.getPickupDatetime()
      @JvmName("setPickupDatetime")
      set(value) {
        _builder.setPickupDatetime(value)
      }
    /**
     * <pre>
     * The datetime of the trip (pickup and dropoff)
     * </pre>
     *
     * <code>.google.protobuf.Timestamp pickup_datetime = 3;</code>
     */
    public fun clearPickupDatetime() {
      _builder.clearPickupDatetime()
    }
    /**
     * <pre>
     * The datetime of the trip (pickup and dropoff)
     * </pre>
     *
     * <code>.google.protobuf.Timestamp pickup_datetime = 3;</code>
     * @return Whether the pickupDatetime field is set.
     */
    public fun hasPickupDatetime(): kotlin.Boolean {
      return _builder.hasPickupDatetime()
    }

    /**
     * <code>.google.protobuf.Timestamp dropoff_datetime = 4;</code>
     */
    public var dropoffDatetime: com.google.protobuf.Timestamp
      @JvmName("getDropoffDatetime")
      get() = _builder.getDropoffDatetime()
      @JvmName("setDropoffDatetime")
      set(value) {
        _builder.setDropoffDatetime(value)
      }
    /**
     * <code>.google.protobuf.Timestamp dropoff_datetime = 4;</code>
     */
    public fun clearDropoffDatetime() {
      _builder.clearDropoffDatetime()
    }
    /**
     * <code>.google.protobuf.Timestamp dropoff_datetime = 4;</code>
     * @return Whether the dropoffDatetime field is set.
     */
    public fun hasDropoffDatetime(): kotlin.Boolean {
      return _builder.hasDropoffDatetime()
    }

    /**
     * <pre>
     * Number of passengers
     * </pre>
     *
     * <code>int32 passenger_count = 5;</code>
     */
    public var passengerCount: kotlin.Int
      @JvmName("getPassengerCount")
      get() = _builder.getPassengerCount()
      @JvmName("setPassengerCount")
      set(value) {
        _builder.setPassengerCount(value)
      }
    /**
     * <pre>
     * Number of passengers
     * </pre>
     *
     * <code>int32 passenger_count = 5;</code>
     */
    public fun clearPassengerCount() {
      _builder.clearPassengerCount()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun trips.TripsOuterClass.Trip.copy(block: trips.TripKt.Dsl.() -> kotlin.Unit): trips.TripsOuterClass.Trip =
  trips.TripKt.Dsl._create(this.toBuilder()).apply { block() }._build()