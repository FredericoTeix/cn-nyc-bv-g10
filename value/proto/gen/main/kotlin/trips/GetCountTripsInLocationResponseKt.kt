//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: trips.proto

package trips;

@kotlin.jvm.JvmSynthetic
public inline fun getCountTripsInLocationResponse(block: trips.GetCountTripsInLocationResponseKt.Dsl.() -> kotlin.Unit): trips.TripsOuterClass.GetCountTripsInLocationResponse =
  trips.GetCountTripsInLocationResponseKt.Dsl._create(trips.TripsOuterClass.GetCountTripsInLocationResponse.newBuilder()).apply { block() }._build()
public object GetCountTripsInLocationResponseKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: trips.TripsOuterClass.GetCountTripsInLocationResponse.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: trips.TripsOuterClass.GetCountTripsInLocationResponse.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): trips.TripsOuterClass.GetCountTripsInLocationResponse = _builder.build()

    /**
     * <code>int64 count = 1;</code>
     */
    public var count: kotlin.Long
      @JvmName("getCount")
      get() = _builder.getCount()
      @JvmName("setCount")
      set(value) {
        _builder.setCount(value)
      }
    /**
     * <code>int64 count = 1;</code>
     */
    public fun clearCount() {
      _builder.clearCount()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun trips.TripsOuterClass.GetCountTripsInLocationResponse.copy(block: trips.GetCountTripsInLocationResponseKt.Dsl.() -> kotlin.Unit): trips.TripsOuterClass.GetCountTripsInLocationResponse =
  trips.GetCountTripsInLocationResponseKt.Dsl._create(this.toBuilder()).apply { block() }._build()
