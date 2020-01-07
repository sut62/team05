<template>
  <v-app id="inspire">
    <v-app-bar app clipped-right color="#00838F" dark>
      <v-toolbar-title>
        <h1>ระบบยืมอุปกรณ์กีฬา</h1>
      </v-toolbar-title>
      <v-spacer></v-spacer>
    </v-app-bar>
    <v-content>
      <v-card max-width="600" class="mx-auto" color="ffffff">
        <v-container fluid>
    
            <v-col cols="10">
              <v-text-field
                outlined
                label="ID ผู้ใช้งาน"
                v-model="memberId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="CheckID != ''">ชื่อผู้ใช้ : {{name}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="ShowMember_id" depressed large color="primary">Search</v-btn>
              </div>
            </v-col>
   <div v-if="CheckID">

          <v-col cols="12">
            <v-select
              v-model="categoryId"
              :items="categorys"
              item-value="id"
              item-text="category_name"
              label="ประเภทอุปกรณ์กีฬา"
              prepend-icon="mouse"
            ></v-select>
          </v-col>

          <v-col cols="12">
            <v-select
              v-model="sportequipmentId"
              :items="sportequipments"
              item-value="id"
              item-text="se_name"
              label="เลือกอุปกรณ์กีฬา"
              prepend-icon="mdi-tennis"
            ></v-select>
          </v-col>

          <v-col cols="12">
            <v-select
              v-model="employeeId"
              :items="employees"
              item-value="id"
              item-text="name"
              label="Select Employee"
              color="blue"
              prepend-icon="person"
            ></v-select>
          </v-col>
        </div>
        </v-container>
        <v-card-actions color="#0D47A1">
          <v-btn @click="saveBorrow" block color="#00838F" dark>บันทึกการยืมอุปกรณ์</v-btn>
        </v-card-actions>
      </v-card>
    </v-content>

    <v-navigation-drawer v-model="right" fixed right temporary></v-navigation-drawer>

    <v-footer app color="#00838F" class="white--text">
      <span></span>
      <v-spacer></v-spacer>
      <span>&copy;</span>
    </v-footer>
  </v-app>
</template>

<script>
import http from "../http-common";

export default {
  name: "borrow",
  data() {
    return {
      memberId: null,
      sportequipmentId: null,
      categoryId: null,
      employeeId: null,
      right: undefined,
      sportequipments: [],
      employees: [],
      categorys: [],
      CheckID: false,
      name: ""
    };
  },
  methods: {
     ShowMember_id() {
      http
        // .get("/check/" + this.returns.return_id)
        .get("/Members/" + this.returns.member_id)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.name = response.data.name;
            this.CheckID = response.status;
            alert("มี");
          } else {
            this.returns.genderId = "";
            alert("ไม่มี");
          }
        })
        .catch(e => {
          console.log(e);
        });
    },
    getEmployees() {
      http
        .get("/Employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getSportequipments() {
      http
        .get("/sportequipment")
        .then(response => {
          this.sportequipments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
     getCategorys() {
      http
        .get("/category")
        .then(response => {
          this.categorys = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveBorrow() {
      if (!this.memberId || !this.categoryId || !this.sportequipmentId || !this.employeeId) {
        alert("ใส่ข้อมูลไม่ครบถ้วน");
      } else {
        alert("บันทึกการยืมอุปกรณ์");
        http
          .post(
            "/borrow/" +
              this.memberId +
              "/" +
              this.categoryId +
              "/" +
              this.sportequipmentId +
              "/"+
              this.employeeId 
              )
          .then(response => {
            console.log(response);
          })
          .catch(e => {
            console.log(e);
          });
        this.submitted = true;
      }
    },
    refreshList() {
    this.getEmployees();
    this.getSportequipments();
    this.getCategorys();
    }
  },
  mounted() {
    this.getEmployees();
    this.getSportequipments();
    this.getCategorys();
    }
};
</script>