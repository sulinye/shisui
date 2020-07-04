<template>
  <div>
    <el-table :data="tableData">
      <el-table-column type="selection"  disabled='true' width="55"></el-table-column>
      <el-table-column type="index" label="序号"></el-table-column>
      <el-table-column prop="id" label="商品编号"></el-table-column>
      <el-table-column prop="name" label="商品名称"></el-table-column>
      <el-table-column prop="createBy" label="创建人"></el-table-column>
      <el-table-column prop="createTime" label="创建时间" :formatter="createTimeFormatter"></el-table-column>
    </el-table>
  </div>
</template>

<script>

import {findGoodsList} from "@/api/goods";
import dayjs from 'dayjs'
// import axios from 'axios';
export default {
  name:'goods',
  data () {
    return {
      tableData: []
    }
  },

  created() {
    this.findGoodsList();
  },
  mounted(){

  },
  methods: {

    // findGoodsList(){
    //   //需要传参的话可以在地址栏后面拼接
    //             axios.get('http://localhost:61002/V1/goods/findGoodsList',
    //             //还可以这样传参
    //             //     {
    //             //         params:{
    //             //             name:'王富贵'
    //             //         }
    //             //     }
    //             )
    //             .then(res => {
    //                 console.log(res)
    //             })
    // }

    // ok
    // findGoodsList(){
    //   //get方法  只能在地址栏传参
    //   fetch('http://localhost:61002/V1/goods/findGoodsList')
    //     .then(res => {
    //     //return 返回给上一层 ,不然下一层用不了
    //     return res.json()
    //   })
    //   .then(data => {
    //   console.log(data)
    //   })
    // }
    
    findGoodsList(){
      findGoodsList().then(res => {
        console.log(res,'res')
        this.tableData = res.data
      })
    },
    createTimeFormatter (row, column) {
      return this.dateFormatter(row.createTime)
    },
    dateFormatter (dateObj) {
      return dateObj ? dayjs(dateObj).format('YYYY-MM-DD HH:mm:ss') : ''
    }
    // findGoodsList(){
    //     // let __this = this;
    //         // signIn().then(res => {
    //         //   __this.id = res.data.boardStyle.id
    //         //   __this.code = res.data.boardStyle.code
    //         // })

    //         // let __this = this;
    //         // axios.get(' https://easy-mock.com/mock/5d1317c17bb925712e680cc9/OppoColors/V1/board/getBoardUserData')
    //         // this.$http.get('http://localhost:61002/V1/goods/findGoodsList')
    //         //   .then(res=>{
    //         //     console.log(res,'调用localhost')
    //         //     // __this.id =res.data.data.id
    //         //     // __this.name = res.data.data.nickName
    //         //     // __this.signature =res.data.data.signature
    //         //   })

    //         this.$http.get('http://localhost:61002/V1/goods/findGoodsList')
    //         .then(res=>{
    //           console.log(res,"res");
              
    //         }) 
    // }
  }
}
</script>