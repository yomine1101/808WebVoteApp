# 線上投票系統

#### 登入頁面 login
| 角色   | 帳號   | 密碼   |
| ------ | ------ | ------ |
| 管理人員 | admin  | admin  |
| 使用者 | 0000   | 0000   |

![登入](images/login.png)
#### 管理人員頁面 update/insert/delete
![管理人員頁面](images/admin.png)
![管理人員頁面](images/admin2.png)
#### 使用者頁面 insert/voteItemCount
![使用者頁面](images/user.png)

# 項目結構
## frontend
```
├── public/
├── src/
│ ├── assets/
│ ├── components/
│ │ ├── AdminPage.vue # 管理人員頁面
│ │ ├── UserPage.vue  # 使用者頁面
│ │ ├── LoginPage.vue # 登入頁面
│ │ └── VoteForm.vue  # 投票選項清單
│ ├── App.vue 
│ ├── main.js 
│ ├── router.js # 路由配置文件
├── package.json 
└── ...
```
## backend
```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── webvoteapp/
│   │   │               ├── WebvoteappApplication.java   
│   │   │               ├── controller/
│   │   │               │   ├── VoteItemController.java   # 處理投票項目的請求
│   │   │               │   ├── VoteController.java       # 處理使用者投票的請求
│   │   │               │   └── UserController.java       # 使用者登入
│   │   │               ├── model/
│   │   │               │   ├── VoteItem.java             
│   │   │               │   ├── Vote.java                 
│   │   │               │   └── User.java                
│   │   │               ├── repository/
│   │   │               │   ├── VoteItemRepository.java   
│   │   │               │   ├── VoteRepository.java      
│   │   │               │   └── UserRepository.java       
│   │   │               ├── service/
│   │   │               │   ├── VoteItemService.java      # 處理投票項目業務邏輯
│   │   │               │   ├── VoteService.java          # 處理使用者投票業務邏輯
│   │   │               │   └── UserService.java          # 處理使用者業務邏輯
│   │   │               └── config/
│   │   │                   └── SecurityConfig.java       # 安全配置類
└── ...

```

