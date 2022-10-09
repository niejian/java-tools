package cn.com.code4fun.proto;

//import cn.com.code4fun.proto.dto.PersonDto;

import cn.com.code4fun.proto.dto.Person;

/**
 * @className: Test
 * @desc:
 * @time: 2022/10/9 14:07
 * @version: 0.0.1
 */
public class Test {
    public static void main(String[] args) {
        Person.PersonDto.PhoneNumber.Builder phone = Person.PersonDto.PhoneNumber.newBuilder().setNumber("110")
                .setPhoneType(Person.PersonDto.PhoneNumber.PhoneType.Mobile);

        Person.PersonDto person = Person.PersonDto.newBuilder()
                .setEmail("1234@qq.com")
                .setSex(Person.PersonDto.Sex.Female)
                .addPhoneNumbers(phone.build())
                .putTags("a", "mail")
                .setId(111)
                .setName("zhangsan").build();

        System.out.println(person.toString());
    }
}
