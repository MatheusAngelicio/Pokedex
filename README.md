
# Pokedex

Application lists all pokémon with pagination and by clicking on a pokemon you can see its details


![logoPokedex](https://user-images.githubusercontent.com/54027680/166256641-fd8e2c3a-d0f0-4f62-bb26-62c089182589.png)





## API Reference: https://pokeapi.co/

#### Get all pokemons

```http
  GET /api/v2/pokemon?limit=20&offset=0
```

| Parameter | Type     | 
| :-------- | :------- | 
| `count` | `Int` |
| `next` | `String` |
| `previous` | `String` |
| `results` | `List<Results>` |

#### Get pokemon item

```http
  GET /api/v2/pokemon/${id}

```

| Parameter | Type     | 
| :-------- | :------- | 
| `name`      | `String` | 
| `height`      | `Int` | 
| `weight`      | `Int` | 
| `stats`      | `List<Stats>` | 
| `moves`      | `List<Moves>` | 
| `types`      | `List<Types>` | 
| `sprites`      | `Sprites` | 
| `abilities`      | `List<Abilities>` | 


## 🚀 Technologies
* Kotlin
* Architecture MVVM
* Dependency injection - Dagger Hilt
* LiveData
* Retrofit
* Moshi
* Coroutines
* Lottie
* Glide
* ProgressView



## Screenshots

![pokedex01](https://user-images.githubusercontent.com/54027680/166258085-43fe4875-cb1e-4597-873c-9cb37548d198.jpeg)   ![pokedex02](https://user-images.githubusercontent.com/54027680/166258301-0d075cf8-254d-4f32-b134-3e898b648e58.jpeg)   ![pokedex03](https://user-images.githubusercontent.com/54027680/166258398-6f71544d-680d-47ef-9684-a5bcccc0cba8.jpeg)




