<template>
  <input v-model="city" placeholder="이걸 본 너는 바보가 된다">
  <button @click="initLoad()">조회하기</button>

  

  <div>
    <table style="width:60%; height: 100px; margin: auto; text-align: center;">
      <tr>
        <th>날짜</th>
        <th>날씨</th>
        <th>최저 온도</th>
        <th>최고 온도</th>
      </tr>
      <tr v-for="item in items" :key="item.title">
        <td><span v-html="item.날짜"></span></td>
        <td><span v-html="item.날씨"></span></td>
        <td><span v-html="item.최저온도"></span></td>
        <td><span v-html="item.최고온도"></span></td>
      </tr>
    </table>
  </div>

</template>

<!-- <template>
  <div class="test">
    <h1>This is a Test page. </h1>
    <div id="demo">
        <button @click="submitBtn()">post하기</button>
    </div>
  </div>
</template> -->


<script>
import axios from 'axios';
// import Movie from './components/Movie.vue'
// import Test from './components/Test.vue'

export default {
  name: 'App',
  components: {
    // Movie
    // Test
  },
  data() {
    return {
      errors: [],
      items: [],

      threadPool: [],
      city: ""
    }
  },
  mounted() {
    this.test()
  },
  methods: {
    test() {
      // var array = [];
      // var dict = {
      //   "날씨": "value1",
      //   "날짜": "value2"
      // };

      // array.push(dict);
      // console.log(array);
      // Repeat this last part as needed to add more key/value pairs
    },
    initLoad() {
    	axios.get('/api/city/'+this.city)
        .then(response => (response.data))
        .then(result => {
          
          // JSON responses are automatically parsed.
          this.threadPool = result;
          this.items = [];

          var dict = {};

          for (var i = 0; i < this.threadPool.length; i++) {
            dict = {
              날짜: this.threadPool[i][0],
              날씨: this.threadPool[i][1],
              최저온도: this.threadPool[i][2],
              최고온도: this.threadPool[i][3],
            };

            console.log(dict);

            // this.dic["날짜"] = this.threadPool[i][0];
            // this.dic["날씨"] = this.threadPool[i][1];
            // this.dic["최저 온도"] = this.threadPool[i][2];
            // this.dic["최고 온도"] = this.threadPool[i][3];
            this.items.push(dict);
            console.log(this.items);
          }

          return this.items
        })
        .catch(e => {
          this.errors.push(e)
        })
	  }
  }
}
</script>

<style>
#app {
  td, th {
    padding: 10px;
    border: 1px solid #ccc;
  }
  body {
  padding: 1rem;
  }
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
