## Tutorial 6
### What I have learned today
- Perbedaan otentikasi dan otorisasi
- Mengimplementasikan Spring Boot Starter Security
- Membuat otorisasi fitur yang berbeda-beda untuk tiap role dari user
- Menyimpan password milik user dalam bentuk hashed

### Pertanyaan
1.Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi?

Otentikasi adalah proses identifikasi pengguna apakah pengguna terdaftar dalam suatu sistem aplikasi. Proses otentikasi akan melakukan verifikasi terhadap siapa yang mencoba untuk mengakses sistem aplikasi (verifikasi identitas pengguna). 

Otorisasi adalah proses menentukan apakah pengguna saat ini (yang sudah terotentikasi) diperbolehkan atau diberikan suatu akses untuk melakukan tugas/action tertentu ataukah tidak. Tugas/action ini dapat disebut juga melakukan manipulasi terhadap suatu sumber daya tertentu. 
Untuk otentikasi, konsep tersebut diimplementasikan pada bagian ketika user ingin melakukan login. Ketika user memasukkan username dan password, username dan password tersebut akan dicek apakah terdapat pada database dari UserModel. Jika ya, maka user teridentifikasi sebagai pengguna yang terdaftar dalam suatu sistem aplikasi. Berikut ini adalah kodenya pada class WebSecurityConfig.java :
![img.png](img.png)
 
Untuk otorisasi, konsep tersebut diimplementasikan pada bagian ketika user ingin melakukan tugas/action tertentu, misalnya menambah destinasi. Pengguna yang boleh menambah destinasi hanya user yang memiliki role Agen. User dengan role lain hanya dapat melihat detail saja, namun tidak dapat mengakses atau melihat halaman tambah (termasuk tombol tambah). Hal tersebut diimplementasikan dengan pengecekan role Agen terlebih dahulu. Jika role sesuai, maka user dapat melakukan tugas/action atau melakukan manipulasi sumber daya tertentu pada aplikasi. Berikut ini adalah kodenya pada WebSecurityConfig.java :
![img_1.png](img_1.png)

Sumber :

https://www.sailpoint.com/identity-library/difference-between-authentication-and-authorization/#:~:text=Simply%20put%2C%20authentication%20is%20the,a%20user%20has%20access%20to.


2.Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerja dan tujuannya.

BcryptPasswordEncoder adalah Implementasi PasswordEncoder yang menggunakan fungsi hashing kuat BCrypt. Klien dapat secara opsional menyediakan "kekuatan" (alias log rounds di BCrypt) dan instansi SecureRandom. Semakin besar parameter kekuatan, semakin banyak pekerjaan yang harus dilakukan (secara eksponensial) untuk meng-hash kata sandi. Nilai defaultnya adalah 10.
Cara kerjanya yaitu BCryptPasswordEncoder secara otomatis menghasilkan dan menggunakan random salt untuk menghitung hash. Setiap panggilan, akan memiliki hasil yang berbeda, sehingga kita hanya perlu mengkodekan sandi sekali. Untuk membuat salt generation bekerja, BCrypt akan menyimpan salt didalam nilai hash itu sendiri. Misalnya, hash value nya adalah

$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq

1. "2a" mewakili versi algoritma Bcrypt
2. "10" mewakili kekuatan algoritme
3. "ZLhnHxdpHETcxmtEStgpI." adalah bagian generated salt. Pada dasarnya, 22 karakter pertama adalah salt. Bagian yang tersisa dari bidang terakhir adalah versi hash sebenarnya dari teks biasa. 

Perlu diketahui juga bahwa algoritma BCrypt menghasilkan String dengan panjang 60, jadi kita perlu memastikan bahwa kata sandi akan disimpan dalam kolom yang dapat menampungnya. Kesalahan umum adalah membuat kolom dengan panjang yang berbeda dan kemudian mendapatkan kesalahan Nama Pengguna atau Kata Sandi Tidak Valid pada waktu otentikasi. 

Aplikasi Spring Boot menggunakan BCryptPasswordEncoder dengan tujuan untuk menyandikan dan memvalidasi kata sandi pengguna untuk aplikasi tersebut. BCryptPasswordEncoder default adalah implementasi default dari algoritma BCrypt. Kelas BCryptPasswordEncoder memungkinkan kompleksitas algoritma pengkodean ditingkatkan dengan menggunakan nilai kekuatan sebagai argumen.

Sumber :

https://www.yawintutor.com/bcryptpasswordencoder-bad-strength/

https://docs.spring.io/spring-security/site/docs/3.2.5.RELEASE/apidocs/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html

https://www.baeldung.com/spring-security-registration-password-encoding-bcrypt

3. Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa demikian?

Penyimpanan password sebaiknya menggunakan hashing, karena hashing adalah teknik enkripsi satu arah yang berarti bahwa tidak mungkin untuk merekayasa balik nilai hash untuk mendapatkan kembali teks asli (plain text) passwordnya. Password membutuhkan proses yang aman seperti itu. Kata sandi pengguna dijalankan melalui fungsi hash dan disimpan dalam database. Setiap kali pengguna masuk, database meng-hash kata sandi yang mereka masukkan dan memeriksa untuk melihat apakah hash yang dimasukkan cocok dengan hash yang mereka miliki di file. Jika ya, pengguna dapat masuk. Dengan hash, aplikasi/situs tidak pernah menyimpan kata sandi yang sebenarnya di mana pun, dan peretas yang membobol hanya akan mendapatkan daftar huruf dan angka yang tidak dapat didekodekan. Bergantung pada seberapa kuat algoritmanya, hash ini bisa sangat sulit untuk dipecahkan. 

Sumber:

https://www.maketecheasier.com/password-hashing-encryption/

4. Jelaskan secara singkat apa itu UUID beserta penggunaannya!

UUID adalah singkatan dari Universal Unique Identifier. UUID ini adalah angka 128-bit yang digunakan untuk mengidentifikasi secara unik suatu objek atau entitas di internet. UUID terdiri dari 16 oktet dan direpresentasikan sebagai 32 base-16 character, yang dapat digunakan untuk mengidentifikasi informasi lintas atau di seluruh sistem komputer. 

UUID umumnya digunakan untuk mengidentifikasi informasi yang perlu unik dalam sistem atau jaringannya. Keunikannya membuatnya berguna untuk menjadi kunci asosiatif dalam database dan pengidentifikasi untuk perangkat keras fisik dalam suatu organisasi. 

Sumber :

https://searchapparchitecture.techtarget.com/definition/UUID-Universal-Unique-Identifier

https://duo.com/labs/tech-notes/breaking-down-uuids

5. Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut padahal kita sudah memiliki class UserRoleServiceImpl.java?

Class UserDetailsServiceImpl.java adalah sebuah class java yang mengimplementasikan interface UserDetailsService yang diimport dari org.springframework.security.core. UserDetailsServiceImpl.java berguna sebagai implementasi read-only method yaitu mengambil atau mengakses data pengguna (user-specific data) dalam database, dalam hal ini adalah database untuk UserModel (user). Nama methodnya adalah loadUserByUsername(String username) yang akan menemukan pengguna berdasarkan parameter username.  

Kita tetap membutuhkan class tersebut karena UserDetailsServiceImpl.java dan UserRoleServiceImpl.java memiliki fungsi yang berbeda. UserRoleServiceImpl.java berfungsi sebagai akses kepada database untuk RoleModel (role) sedangkan UserDetailsServiceImpl.java berfungsi sebagai akses kepada database untuk UserModel (user). Selain itu, UserDetailsServiceImpl.java sangat penting karena digunakan sebagai sarana otentikasi pengguna aplikasi dengan cara mengakses data UserModel dalam database. 

Sumber :

https://docs.spring.io/spring-security/site/docs/3.0.x/apidocs/org/springframework/security/core/userdetails/UserDetailsService.html

### What I did not understand
- [ ] Penggunaan Spring Boot Starter Security secara lebih mendalam.
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))


## Tutorial 5 
### What I have learned today
- Belajar bagaimana mengimplementasikan web service
- Belajar cara membuat dan mengakses web service dengan berbagai method yang ada
- Belajar cara menggunakan postman

### Pertanyaan

1.Apa itu Postman? Apa kegunaannya?
Postman adalah aplikasi yang berfungsi sebagai Rest Client. Aplikasi ini digunakan untuk uji coba Rest API. Postman biasa digunakan oleh pengembang pembuat API sebagai alat untuk menguji API yang telah dibuat. 

Sumber : 
https://antares.id/id/postman.html

2. Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.
Fungsi :

@JsonIgnoreProperties digunakan di tingkat kelas untuk menandai properti atau daftar properti yang akan diabaikan. @JsonIgnoreProperties dianotasi di tingkat kelas dan kita perlu menentukan properti logis dari kelas itu untuk mengabaikannya. @JsonIgnoreProperties memiliki elemen yang allowGetters, allowSetter, ignoreUnknown dan value. Elemen value di @JsonIgnoreProperties menentukan nama properti yang akan diabaikan.

@JsonProperty mendefinisikan properti logis yang digunakan dalam serialisasi dan deserialisasi JSON. Ketika kita mengatur data JSON ke Objek Java, hal itu disebut deserialization JSON dan ketika kita mendapatkan data JSON dari Java Object, hal itu disebut serialisasi JSON. @JsonProperty dapat mengubah visibilitas properti logis menggunakan elemen aksesnya selama serialisasi dan deserialisasi JSON. @JsonProperty dapat dianotasi di non-static setter or getter method atau non-static object field.

Sumber : 

https://www.concretepage.com/jackson-api/jackson-jsonproperty-and-jsonalias-example

https://www.tutorialspoint.com/jackson_annotations/jackson_annotations_jsonignoreproperties.htm

https://www.concretepage.com/jackson-api/jackson-jsonignore-jsonignoreproperties-and-jsonignoretype

3. Apa kegunaan atribut WebClient?

WebClient adalah antarmuka yang mewakili titik masuk utama untuk melakukan web requests. WebClient dibuat sebagai bagian dari modul Spring Web Reactive, dan merupakan klien web non-pemblokiran (non-blocking) dan reaktif untuk melakukan HTTP request, memperlihatkan API reaktif yang fluent melalui HTTP client libraries seperti Reactor Netty.  

Sumber :

https://www.baeldung.com/spring-5-webclient

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/client/WebClient.html

4. Apa itu ResponseEntity dan BindingResult? Apa kegunaannya? 

ResponseEntity adalah ekstensi HttpEntity yang menambahkan HttpStatus status code. Digunakan di RestTemplate serta dalam @Controller method. ResponseEntity mewakili seluruh respons HTTP: kode status, header, dan isi. Kegunaannya adalah untuk manipulasi HTTP response dengan mengatur body, status dan header dari HTTP response.

BindingResult adalah antarmuka umum yang merepresentasikan binding result (hasil meningkat). Kegunaannya adalah untuk memperluas antarmuka untuk kemampuan registrasi kesalahan, memungkinkan Validator untuk diterapkan, dan menambahkan analisis khusus pengikatan dan pembuatan model.

Sumber :

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html

https://www.baeldung.com/spring-response-entity

https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/validation/BindingResult.html

### What I did not understand
- [ ] Masih belum sepenuhnya memahami mekanisme mengenai web service seutuhnya.
(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

=======

## Tutorial 4

### What I have learned today

- Saya belajar konsep view pada Spring Boot
- Saya belajar apa itu Thymeleaf dan kegunaannya

### Pertanyaan

1. Jelaskan perbedaan th:include dan th:replace!

th:include => memasukkan hanya konten dari fragmen yang ditentukan kepada atau sebagai bagian dari tagnya. Atribut th:include ini menyisipkan fragmen yang ditentukan sebagai badan tag inangnya tetapi tanpa menyisipkan fragment tag nya.

th:replace => mengganti (replace) tag inangnya nya dengan fragmen yang ditentukan atau didefinisikan pada tag inang tersebut. Atribut th:replace ini akan menggantikan tag host (inang) dengan fragmen tersebut. Hal tersebut berarti akan menghapus tag inang dan menggantikan tag inang dengan konten fragmen beserta fragment tag nya.

Sumber :

https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html

2. Jelaskan apa fungsi dari th:object!

Fungsi dari atribut th:object yaitu untuk menentukan objek yang terikat oleh data formulir yang dikirimkan. Masing-masing field dipetakan menggunakan atribut th:field=”*{nama}”, dimana ‘nama’ adalah properti objek yang sesuai dengan objek tersebut. Atribut th:object mendefinisikan objek mana yang dirujuk oleh _field_.

Sumber :

https://www.baeldung.com/thymeleaf-in-spring-mvc

https://stackabuse.com/getting-started-with-thymeleaf-in-java-and-spring/


3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?

syntax $ => digunakan untuk menspesifikkan objek pada saat menggunakan atribut th:object. Syntax ${} pada atribut th:object dipakai ketika ingin memilih objek yang ingin digunakan.

syntax * => digunakan untuk menspesifikkan properti objek dari objek yang didefinisikan pada atribut th:object. Ketika suatu object telah didefinisikan pada atribut th:object dengan syntax ${}, maka kita dapat menggunakan syntax *{} untuk menspesifikkan properti dari objek tersebut. 

Sumber:

https://o7planning.org/12385/thymeleaf-th-object-and-asterisk-syntax

### What I did not understand

- [ ] Masih belum memahami syntax dari penggunaan Thymeleaf

(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

## Tutorial 3

### What I have learned today
-	Saya belajar Create, Read, Update, dan Delete (CRUD) pada basis data dengan menggunakan konsep MVC dalam project Spring Boot.
-	Saya belajar model yang terhubung dengan basis data.
-	Saya belajar JPARepository untuk melakukan query pada basis data.

### Pertanyaan

1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)
   Kegunaan :

@AllArgsConstructor : lombok menghasilkan konstruktor dengan 1 parameter untuk setiap field di class kita.

@NoArgsConstructor : lombok menghasilkan konstruktor tanpa parameter.

@Setter : lombok membuat metode default untuk set (setter) nilai atribut/field secara otomatis.

@Getter : lombok membuat metode default untuk get (getter) nilai atribut/field secara otomatis.

@Entity : mendefinisikan sebuah entitas agar JPA mengetahui terdapat entitas tersebut.

@Table : menspesifikasikan nama tabel dalam database karena biasanya nama tabel dalam database dan nama entitas tidak sama.

Sumber :
https://projectlombok.org/features/constructor
https://projectlombok.org/features/GetterSetter
https://www.baeldung.com/jpa-entities

2. Pada class TravelAgensiDb, terdapat method findByNoAgensi, apakah kegunaan dari method tersebut?

Kegunaan method findByNoAgensi yang terdapat pada class TravelAgensiDb adalah untuk mencari suatu travel agensi di database tabel Travel Agensi berdasarkan parameter noAgensi yang di passing ke method tersebut. Method ini nantinya akan mengembalikan objek TravelAgensiModel sesuai dengan noAgensi yang diminta.  

3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn

Kegunaan :

@JoinTable : untuk mendefinisikan tabel join/link. Anotasi ini digunakan untuk menentukan pemetaan asosiasi entitas.

@JoinColumn : untuk menentukan kolom join/link dengan tabel utama, yaitu untuk memetakan kolom gabungan database dalam entitas (kolom yang tergabung ke asosiasi entitas atau koleksi elemen).

Sumber :
https://www.baeldung.com/hibernate-many-to-many
https://javabydeveloper.com/difference-between-joincolumn-and-mappedby/
https://docs.oracle.com/javaee/7/api/javax/persistence/JoinTable.html

4. Pada class TourGuideModel, digunakan anotasi @JoinColumn pada atribut agensi, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull

Kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut menunjukkan kolom dari AgensiModel yang di-join atau diasosiasikan dengan tabel utama (TourGuideModel).

Perbedaan :

Nullable : nullable didefinisikan sebagai bagian dari spesifikasi Java Persistence API. Hal ini digunakan terutama dalam pembuatan metadata skema DDL. Hal ini berarti bahwa jika kita membiarkan Hibernate menghasilkan skema database secara otomatis, itu menerapkan not null constraint ke kolom database tertentu.

@NotNull : Anotasi @NotNull didefinisikan dalam spesifikasi Bean Validation. Hal ini berarti penggunaannya tidak terbatas hanya pada entitas. Hibernate tidak memicu SQL insert statement. Akibatnya, data yang tidak valid tidak disimpan ke database (terdapat validasi terlebih dahulu).

Sumber :
https://www.baeldung.com/hibernate-notnull-vs-nullable

5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER

Kegunaan :

FetchType.LAZY : mengambil data dari database dengan cara memuatnya sesuai permintaan (ketika ingin mengakses saja), misalnya saat terdapat suatu method get.

CascadeType.ALL : menyebarkan (cascade) semua operasi EntityManager (PERSIST, REMOVE, REFRESH, MERGE, DETACH) ke entitas terkait.

FetchType.EAGER : mengambil data dari database dengan cara memuatnya bersamaan dengan field lainnya atau ketika parent mereka diambil dari database.

Sumber :
https://newbedev.com/difference-between-fetchtype-lazy-and-eager-in-java-persistence-api
https://newbedev.com/what-is-the-meaning-of-the-cascadetype-all-for-a-manytoone-jpa-association

### What I did not understand
- [ ] Masih belum begitu memahami penggunaan JPA Repository
  (Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
  lebih dalam tentang penulisan README.md di GitHub pada link
  [berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

---

## Tutorial 2

### What I have learned today
- Saya belajar bagaimana caranya melakukan organizing domain logic & service layer. 
- Saya mempelajari konsep MVC dengan mempelajari tentang model dan service.
- Saya belajar membuat sebuah model dengan konsep MVC dalam project Spring Boot.
- Saya belajar membuat service untuk create & read data menggunakan konsep MVC dalam project Spring Boot

### Pertanyaan
1. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut:
http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx

Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi

![img_8.png](img_8.png)

Ketika mengakses link tersebut terjadi error bernama “Whitelabel Error Page”. Hal ini dikarenakan view template yang telah dicantumkan pada Controller, yaitu “add-agensi” belum dibuat sehingga Controller tidak dapat me¬ngembalikan view template tersebut dan akhirnya request mapping saat routing URL tidak dapat dilakukan. Controller tidak dapat melakukan resolving template “add-agensi” karena template belum dibuat dan akhirnya menyebabkan error tersebut. 

2. Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat

Menurut saya, anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep dependency injection pada Spring Framework. Cara kerjanya :
1. Fitur component-scan pada Spring Framework akan melihat isi package yang kita sebutkan, lalu akan mencari class-class yang diberikan anotasi @Repository, @Service, @Controller, dan @Component, dalam hal ini adalah class TravelAgensiController. 
2. Setelah ditemukan, Spring FrameWork akan melakukan inisialisasi terhadap class tersebut, dan melakukan dependency injection dengan cara mengisi semua kebutuhan class TravelAgensiController, dalam hal ini adalah kebutuhan untuk melakukan inisialisasi objek atau properti TravelAgensiService. 
3. Anotasi @Autowired membuat dependency injection tidak memerlukan setter ataupun constructor injection. 
4. Dengan anotasi @Autowired, properti TravelAgensiService akan diisikan oleh Spring dengan object bertipe-data sesuai, dalam hal ini adalah tipe data TravelAgensiService. Dengan kata lain, anotasi @Autowired ini akan melakukan inisialisasi objek TravelAgensiService untuk keperluan kebutuhan class TravelAgensiController.
5. Objek tersebut digunakan oleh class TravelAgensiController untuk membantu bagian Controller dalam melaksanakan layanan manipulasi class TravelAgensi pada bagian Service yang berisi interface TravelAgensiService beserta class yang mengimplementasikan interface tersebut. 

Sumber : https://software.endy.muhardin.com/java/memahami-dependency-injection/

3. Cobalah untuk menambahkan sebuah Agensi dengan mengakses link berikut:
http://localhost:8080/agensi/add?idAgensi=1&namaAgensi=Papa%20APAP&alamat=Maung%20Fasilkom

Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.

![img_9.png](img_9.png)

Yang terjadi ketika mengakses link tersebut adalah error bernama “Whitelabel Error Page”. Hal ini dikarenakan pada link untuk add agensi tersebut tidak mengandung parameter noTelepon (required requested parameter ‘noTelepon’). Sedangkan value noTelepon dengan tipe String merupakan salah satu value RequestParam yang diperlukan dalam melakukan RequestMapping saat routing URL untuk melakukan penambahan agensi. Maka, terjadilah error. 

4. Jika Papa APAP ingin melihat Travel Agensi dengan nama Papa APAP, link apa yang harus diakses?
http://localhost:8080/agensi/view?idAgensi=1

![img_2.png](img_2.png)

5. Tambahkan 1 contoh Travel Agensi lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/agensi/viewAll , apa yang akan ditampilkan?
Sertakan juga bukti screenshotmu

Penambahan 1 contoh Travel Agensi bernama Zeberina, dengan ID = 23, alamat nya Batan Indah, dan nomor teleponnya 08129

Yang akan ditampilkan oleh link diatas adalah daftar TravelAgensi yang ditambahkan, termasuk yang baru saja ditambahkan. 

Link penambahan TravelAgensi :
http://localhost:8080/agensi/add?idAgensi=23&namaAgensi=Zeberina&alamat=Batan%20Indah&noTelepon=08129

Tampilan HTML setelah berhasil menambahkan :

![img_6.png](img_6.png)

Tampilan viewAll HTML setelah ditambahkan :

![img_7.png](img_7.png)

### Latihan
1. Pada TravelAgensiController tambahkan sebuah method view Agensi dengan menggunakan Path Variable. Misalnya, kamu ingin memasukkan data sebuah Agensi yang memiliki idAgensi 1, untuk melihat data yang baru dimasukkan tersebut, user dapat mengakses halaman 
http://localhost:8080/agensi/view/id-agensi/1

![img_5.png](img_5.png)

2. Tambahkan fitur untuk melakukan update noTelepon Agensi berdasarkan idAgensi. Misalnya, setelah melakukan add Agensi pada tahap 1 bab View Template, cobalah untuk mengubah noTelepon objek Agensi tersebut menjadi “021752xxxx” dengan mengakses halaman
http://localhost:8080/agensi/update/id-agensi/1/no-telepon/021752xxxx

Tampilkan juga sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil diubah.

![img_3.png](img_3.png)

4. Tambahkan fitur untuk melakukan delete Agensi berdasarkan idAgensi. Misalnya, setelah melakukan add Agensi pada tahap 1 bab View Template dan melakukan update seperti pada latihan nomor 2, cobalah untuk melakukan delete data tersebut dengan mengakses halaman http://localhost:8080/agensi/delete/id-agensi/1

Tampilkan sebuah halaman yang memberikan informasi bahwa data tersebut telah berhasil dihapus

![img_4.png](img_4.png)

### What I did not understand
- [ ] Masih perlu memahami kode secara keseluruhan
- [ ] Masih perlu memahami konsep MVC secara lebih mendalam

(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))

---

## Tutorial 1

### What I have learned today

(Masukkan pertanyaan yang diikuti jawaban di setiap nomor, contoh seperti dibawah. Anda
juga boleh menambahkan catatan apapun di bagian ini)

### Github

**1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker?**

Issue Tracker di Github adalah alat pelacakan yang terintegrasi dengan repositori GitHub dan digunakan untuk fokus pada tugas-tugas penting dan terus memperbarui rencana secara simultan. Issue Tracker di Github seperti email, namun mereka dapat dibagikan dan didiskusikan dengan anggota tim. Issue Tracker di github memungkinkan kita melacak pekerjaan kita di GitHub, dimana tempat pengembangan kode terjadi. Untuk mengindikasikan bahwa pekerjaan sedang dalam proses, kita dapat menautkan issue kepada pull request. Saat pull request merges, linked issue secara otomatis ditutup.

Masalah yang dapat diselesaikan dengan Issue Tracker adalah masalah pelacakan pada development tasks, request for enhancement, maintain a database of bugs, ideas, dan feedback.

Sumber :

https://firebirdsql.org/en/issue-tracker/
https://guides.github.com/features/issues/
https://docs.github.com/en/issues/tracking-your-work-with-issues/about-issues


**2. Apa perbedaan dari git merge dan git merge --squash?**

Git merge : Git merge tidak membuat perubahan pada riwayat repositori. Git merge hanya membuat satu extra commit untuk merge (penggabungan) tersebut. Cabang tetap mempertahankan history nya dan hanya commit gabungan yang muncul.

Git merge –squash : Git merge –squash menghasilkan working tree dan index state dengan cara yang sama seperti real merge, namun riwayat penggabungan (merge history) dibuang. Cabang tetap mempertahankan history nya, tetapi sekarang tidak ada commit gabungan yang muncul, semua perubahan pada master dikelompokkan menjadi 1 commit dan muncul sebagai commit tunggal di cabang.

Sumber :

https://betterprogramming.pub/git-merge-squash-rebase-or-pull-what-to-choose-50b331d3e7c1
https://dev.to/hectorpascual/git-merge-merge-squash-and-rebase-reflexions-418l


**3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi?**

Keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi adalah dapat menggabungkan repositori file proyek dengan riwayat semua perubahan kode, sehingga mudah untuk mengedit dan memahami kode dari waktu ke waktu. Manfaat utamanya adalah membuat alur kerja tim tetap teratur saat mereka bekerja melalui berbagai jenis perilisan. Dengan suatu repositori, anggota tim dapat dengan mudah meneliti, melacak, dan membatalkan kode. Mereka dapat bekerja pada kode yang sama secara bersamaan tanpa adanya konflik kode. Ditambah lagi, seluruh tim dapat melacak siapa yang membuat perubahan apa, kapan, dan mengapa. Jika terjadi kesalahan, pengembang dapat memutar balik waktu dan membandingkan versi kode sebelumnya untuk membantu memperbaiki kesalahan sambil meminimalkan disruption pada semua anggota tim.

Sumber :

https://backlog.com/blog/git-vs-svn-version-control-system/
https://www.atlassian.com/git/tutorials/what-is-version-control


### Spring

**4. Apa itu library & dependency?**

Library adalah kumpulan kode yang telah ditulis sebelumnya yang dapat digunakan pengguna untuk mengoptimalkan tugas (task). Library adalah precompiled routines yang dapat digunakan oleh suatu program.

Dependency adalah kualitas atau keadaan yang dipengaruhi atau ditentukan oleh atau tunduk pada yang lain. Ketergantungan antara dua komponen adalah ukuran probabilitas bahwa perubahan pada satu komponen dapat mempengaruhi juga komponen lainnya. Dependency Injection adalah proses memasukkan (inject) sebuah class ke dalam class lain. Dalam dependensi ini, kita memberikan/menginjeksi suatu kelas ke kelas yang lain yang merupakan dependensinya (membutuhkannya).

Sumber :

http://blog.rcard.in/programming/oop/software-engineering/2017/04/10/dependency-dot.html
https://www.webopedia.com/definitions/library/
https://www.idtech.com/blog/what-are-libraries-in-coding
https://medium.com/@rakateja/dasar-dependency-injection-442ef9c3b204
https://agung-setiawan.com/java-memahami-dependency-injection/
https://medium.com/koding-kala-weekend/dependency-injection-dan-di-php-2c9d24a885cb


**5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven?**

Apache Maven adalah Java Build Tools yang menggunakan konsep Project Object Model (POM). POM tersebut berisi informasi dan konfigurasi yang digunakan Maven untuk membuat project.

Kita menggunakan Maven karena Maven sendiri merupakan sebuah build automation tool yang memiliki banyak keunggulan, diantaranya adalah Maven membuat struktur project sendiri sehingga project tersebut akan dapat dibuka dengan berbagai IDE dikarenakan Maven mendefinisikan projectnya sendiri. Selain itu memanage Dependency dengan Maven menjadi mudah. Maven sangat berguna bagi programmer Java di dalam proses pengembangan aplikasi mulai dari pembuatan project, mereferensi external library (file-file JAR), pembuatan dokumentasi hingga compiling dan pembuatan application package (misalnya JAR atau WAR). Maven adalah tool yang menemani programmer dari awal hingga akhir proses pengembangan.

Alternative dari Maven diantaranya adalah Gradle, Jira, Jenkins, Apache Tomcat, CMake, dan Apache Ant.

Sumber :

https://medium.com/@acep.abdurohman90/mengenal-maven-sebagai-java-build-tools-5ba752f75812
https://belantara.or.id/document/resource/partnership-programs-in-west-kalimantan-province-resource.pdf
https://stackshare.io/maven/alternatives


**6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework?**

Selain untuk pengembangan web, Spring Framework dapat digunakan untuk mengembangkan aplikasi enterprise. Selain itu, Spring Framework dapat digunakan untuk membuat aplikasi untuk keamanan dan aplikasi yang terkait dengan big data. Spring Framework dapat membuat kode yang high performing, mudah diuji, dan reusable.

Sumber :

https://www.tutorialspoint.com/spring/spring_overview.htm
https://socs.binus.ac.id/2017/10/04/framework-spring-java/


**7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable?**
   
   @RequestParam

- Dapat mengekstrak data yang diberikan melalui query dengan parameter yang sudah ditentukan
- Untuk mendapatkan parameter dari URI.
- Lebih baik digunakan pada aplikasi web tradisional dimana data di passed melalui query dengan parameter yang sudah ditentukan
  
  @PathVariable
  
- Dapat mengekstrak data secara langsung dari URI.
- Untuk mendapatkan tempat penampung dari URI (Spring menyebutnya sebagai Templat URI)
- Lebih baik digunakan untuk layanan web RESTful dimana URL berisi suatu value (data merupakan bagian dari URI dan di passing di URI)

Sumber :

https://javarevisited.blogspot.com/2017/10/differences-between-requestparam-and-pathvariable-annotations-spring-mvc.html#axzz75s6wR6Of
https://www.it-swarm-id.com/id/java/requestparam-vs-pathvariable/1070463371/


### What I did not understand

- [ ] Saya masih tidak mengerti dengan konsep dependency dan dependency injection.
- [ ] Saya masih belum sepenuhnya memahami apa itu Maven dan apa kegunaan dari Maven.
- [ ] Saya masih belum paham secara keseluruhan makna dari kode-kode yang dituliskan. 

