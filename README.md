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

(Anda dapat membuat tampilan code dalam README.md menjadi lebih baik. Cari tahu
lebih dalam tentang penulisan README.md di GitHub pada link
[berikut](https://help.github.com/en/articles/basic-writing-and-formatting-syntax))
