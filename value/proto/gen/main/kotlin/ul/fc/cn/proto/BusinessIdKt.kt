//Generated by the protocol buffer compiler. DO NOT EDIT!
// source: business.proto

package ul.fc.cn.proto;

@kotlin.jvm.JvmSynthetic
public inline fun businessId(block: ul.fc.cn.proto.BusinessIdKt.Dsl.() -> kotlin.Unit): ul.fc.cn.proto.BusinessOuterClass.BusinessId =
  ul.fc.cn.proto.BusinessIdKt.Dsl._create(ul.fc.cn.proto.BusinessOuterClass.BusinessId.newBuilder()).apply { block() }._build()
public object BusinessIdKt {
  @kotlin.OptIn(com.google.protobuf.kotlin.OnlyForUseByGeneratedProtoCode::class)
  @com.google.protobuf.kotlin.ProtoDslMarker
  public class Dsl private constructor(
    private val _builder: ul.fc.cn.proto.BusinessOuterClass.BusinessId.Builder
  ) {
    public companion object {
      @kotlin.jvm.JvmSynthetic
      @kotlin.PublishedApi
      internal fun _create(builder: ul.fc.cn.proto.BusinessOuterClass.BusinessId.Builder): Dsl = Dsl(builder)
    }

    @kotlin.jvm.JvmSynthetic
    @kotlin.PublishedApi
    internal fun _build(): ul.fc.cn.proto.BusinessOuterClass.BusinessId = _builder.build()

    /**
     * <code>int64 id = 1;</code>
     */
    public var id: kotlin.Long
      @JvmName("getId")
      get() = _builder.getId()
      @JvmName("setId")
      set(value) {
        _builder.setId(value)
      }
    /**
     * <code>int64 id = 1;</code>
     */
    public fun clearId() {
      _builder.clearId()
    }
  }
}
@kotlin.jvm.JvmSynthetic
public inline fun ul.fc.cn.proto.BusinessOuterClass.BusinessId.copy(block: ul.fc.cn.proto.BusinessIdKt.Dsl.() -> kotlin.Unit): ul.fc.cn.proto.BusinessOuterClass.BusinessId =
  ul.fc.cn.proto.BusinessIdKt.Dsl._create(this.toBuilder()).apply { block() }._build()