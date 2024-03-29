package kotlinspringboot.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.*
import javax.persistence.*


//实体类:需要初始化
@Entity
data class  User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id:Int=0,
        val username:String="",
        val password:String?=null,
        val age:Int=0,
        val address:String?=null,
        val status:Boolean?=null
)

//api接口类
@RestController
@RequestMapping("api")
class UserController{
        @Autowired
        lateinit var userRepository: UserRepository

        @GetMapping("/findAll")
        fun findAll(): List<User> = userRepository.findAll()

        @PutMapping("/insert")
        fun insert(@RequestBody user:User)= userRepository.save(user)

        @GetMapping("/findByName/{username}")
        fun findByUsername(@PathVariable username:String): User = userRepository.findByUsername(username)

        @GetMapping("/find/{id}")
        fun find(@PathVariable id:Int)= userRepository.findById(id)

        @GetMapping("/findUsernameAndStatus/{username}/{status}")
        fun findUsernameAndStatus(@PathVariable status:Boolean,@PathVariable username: String)= userRepository.findByUsernameAndStatus(username,status)

        @DeleteMapping("/delete/{id}")
        fun delete(@PathVariable id:Int)= userRepository.deleteById(id)
}

interface UserRepository:JpaRepository<User,Int> {
        fun findByUsername(username:String):User
        fun findByUsernameAndStatus(username: String,status: Boolean):User?
}