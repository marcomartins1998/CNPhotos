// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Monitor.proto

package monitorstubs;

/**
 * Protobuf type {@code monitorservice.GlobalStatus}
 */
public  final class GlobalStatus extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:monitorservice.GlobalStatus)
    GlobalStatusOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GlobalStatus.newBuilder() to construct.
  private GlobalStatus(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GlobalStatus() {
    cpuUsage_ = 0D;
    memUsage_ = 0D;
    imgPerSec_ = 0D;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GlobalStatus(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 9: {

            cpuUsage_ = input.readDouble();
            break;
          }
          case 17: {

            memUsage_ = input.readDouble();
            break;
          }
          case 25: {

            imgPerSec_ = input.readDouble();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return monitorstubs.Monitor.internal_static_monitorservice_GlobalStatus_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return monitorstubs.Monitor.internal_static_monitorservice_GlobalStatus_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            monitorstubs.GlobalStatus.class, monitorstubs.GlobalStatus.Builder.class);
  }

  public static final int CPUUSAGE_FIELD_NUMBER = 1;
  private double cpuUsage_;
  /**
   * <code>double cpuUsage = 1;</code>
   */
  public double getCpuUsage() {
    return cpuUsage_;
  }

  public static final int MEMUSAGE_FIELD_NUMBER = 2;
  private double memUsage_;
  /**
   * <code>double memUsage = 2;</code>
   */
  public double getMemUsage() {
    return memUsage_;
  }

  public static final int IMGPERSEC_FIELD_NUMBER = 3;
  private double imgPerSec_;
  /**
   * <code>double imgPerSec = 3;</code>
   */
  public double getImgPerSec() {
    return imgPerSec_;
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (cpuUsage_ != 0D) {
      output.writeDouble(1, cpuUsage_);
    }
    if (memUsage_ != 0D) {
      output.writeDouble(2, memUsage_);
    }
    if (imgPerSec_ != 0D) {
      output.writeDouble(3, imgPerSec_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (cpuUsage_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, cpuUsage_);
    }
    if (memUsage_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(2, memUsage_);
    }
    if (imgPerSec_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, imgPerSec_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof monitorstubs.GlobalStatus)) {
      return super.equals(obj);
    }
    monitorstubs.GlobalStatus other = (monitorstubs.GlobalStatus) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getCpuUsage())
        == java.lang.Double.doubleToLongBits(
            other.getCpuUsage()));
    result = result && (
        java.lang.Double.doubleToLongBits(getMemUsage())
        == java.lang.Double.doubleToLongBits(
            other.getMemUsage()));
    result = result && (
        java.lang.Double.doubleToLongBits(getImgPerSec())
        == java.lang.Double.doubleToLongBits(
            other.getImgPerSec()));
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + CPUUSAGE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getCpuUsage()));
    hash = (37 * hash) + MEMUSAGE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getMemUsage()));
    hash = (37 * hash) + IMGPERSEC_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getImgPerSec()));
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static monitorstubs.GlobalStatus parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static monitorstubs.GlobalStatus parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static monitorstubs.GlobalStatus parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static monitorstubs.GlobalStatus parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static monitorstubs.GlobalStatus parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static monitorstubs.GlobalStatus parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(monitorstubs.GlobalStatus prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code monitorservice.GlobalStatus}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:monitorservice.GlobalStatus)
      monitorstubs.GlobalStatusOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return monitorstubs.Monitor.internal_static_monitorservice_GlobalStatus_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return monitorstubs.Monitor.internal_static_monitorservice_GlobalStatus_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              monitorstubs.GlobalStatus.class, monitorstubs.GlobalStatus.Builder.class);
    }

    // Construct using monitorstubs.GlobalStatus.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      cpuUsage_ = 0D;

      memUsage_ = 0D;

      imgPerSec_ = 0D;

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return monitorstubs.Monitor.internal_static_monitorservice_GlobalStatus_descriptor;
    }

    public monitorstubs.GlobalStatus getDefaultInstanceForType() {
      return monitorstubs.GlobalStatus.getDefaultInstance();
    }

    public monitorstubs.GlobalStatus build() {
      monitorstubs.GlobalStatus result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public monitorstubs.GlobalStatus buildPartial() {
      monitorstubs.GlobalStatus result = new monitorstubs.GlobalStatus(this);
      result.cpuUsage_ = cpuUsage_;
      result.memUsage_ = memUsage_;
      result.imgPerSec_ = imgPerSec_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof monitorstubs.GlobalStatus) {
        return mergeFrom((monitorstubs.GlobalStatus)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(monitorstubs.GlobalStatus other) {
      if (other == monitorstubs.GlobalStatus.getDefaultInstance()) return this;
      if (other.getCpuUsage() != 0D) {
        setCpuUsage(other.getCpuUsage());
      }
      if (other.getMemUsage() != 0D) {
        setMemUsage(other.getMemUsage());
      }
      if (other.getImgPerSec() != 0D) {
        setImgPerSec(other.getImgPerSec());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      monitorstubs.GlobalStatus parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (monitorstubs.GlobalStatus) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double cpuUsage_ ;
    /**
     * <code>double cpuUsage = 1;</code>
     */
    public double getCpuUsage() {
      return cpuUsage_;
    }
    /**
     * <code>double cpuUsage = 1;</code>
     */
    public Builder setCpuUsage(double value) {
      
      cpuUsage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double cpuUsage = 1;</code>
     */
    public Builder clearCpuUsage() {
      
      cpuUsage_ = 0D;
      onChanged();
      return this;
    }

    private double memUsage_ ;
    /**
     * <code>double memUsage = 2;</code>
     */
    public double getMemUsage() {
      return memUsage_;
    }
    /**
     * <code>double memUsage = 2;</code>
     */
    public Builder setMemUsage(double value) {
      
      memUsage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double memUsage = 2;</code>
     */
    public Builder clearMemUsage() {
      
      memUsage_ = 0D;
      onChanged();
      return this;
    }

    private double imgPerSec_ ;
    /**
     * <code>double imgPerSec = 3;</code>
     */
    public double getImgPerSec() {
      return imgPerSec_;
    }
    /**
     * <code>double imgPerSec = 3;</code>
     */
    public Builder setImgPerSec(double value) {
      
      imgPerSec_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double imgPerSec = 3;</code>
     */
    public Builder clearImgPerSec() {
      
      imgPerSec_ = 0D;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:monitorservice.GlobalStatus)
  }

  // @@protoc_insertion_point(class_scope:monitorservice.GlobalStatus)
  private static final monitorstubs.GlobalStatus DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new monitorstubs.GlobalStatus();
  }

  public static monitorstubs.GlobalStatus getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GlobalStatus>
      PARSER = new com.google.protobuf.AbstractParser<GlobalStatus>() {
    public GlobalStatus parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GlobalStatus(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GlobalStatus> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GlobalStatus> getParserForType() {
    return PARSER;
  }

  public monitorstubs.GlobalStatus getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

