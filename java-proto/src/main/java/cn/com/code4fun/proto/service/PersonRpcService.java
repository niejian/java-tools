// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: personRpc.proto

package cn.com.code4fun.proto.service;

public final class PersonRpcService {
  private PersonRpcService() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_com_code4fun_proto_service_QueryPersonByUsernameRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cn_com_code4fun_proto_service_QueryPersonByUsernameRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_cn_com_code4fun_proto_service_PersonResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_cn_com_code4fun_proto_service_PersonResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\017personRpc.proto\022\035cn.com.code4fun.proto" +
      ".service\032\014person.proto\"0\n\034QueryPersonByU" +
      "sernameRequest\022\020\n\010username\030\001 \001(\t\"J\n\016Pers" +
      "onResponse\0228\n\nperson_dto\030\001 \001(\0132$.cn.com." +
      "code4fun.proto.dto.PersonDto2\223\001\n\rPersonS" +
      "ervice\022\201\001\n\023getPersonByUsername\022;.cn.com." +
      "code4fun.proto.service.QueryPersonByUser" +
      "nameRequest\032-.cn.com.code4fun.proto.serv" +
      "ice.PersonResponseB3\n\035cn.com.code4fun.pr" +
      "oto.serviceB\020PersonRpcServiceP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          cn.com.code4fun.proto.dto.Person.getDescriptor(),
        });
    internal_static_cn_com_code4fun_proto_service_QueryPersonByUsernameRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_cn_com_code4fun_proto_service_QueryPersonByUsernameRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cn_com_code4fun_proto_service_QueryPersonByUsernameRequest_descriptor,
        new java.lang.String[] { "Username", });
    internal_static_cn_com_code4fun_proto_service_PersonResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_cn_com_code4fun_proto_service_PersonResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_cn_com_code4fun_proto_service_PersonResponse_descriptor,
        new java.lang.String[] { "PersonDto", });
    cn.com.code4fun.proto.dto.Person.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
