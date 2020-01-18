<template>
  <v-card class="mx-auto" height="100%" width="100%">
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
      <br />
      <v-card class="mx-auto" max-width="850" color="#8B636C">
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>

        <v-row justify="center">
          <v-toolbar-title>
            <h1>ยืมอุปกรณ์กีฬา</h1>
          </v-toolbar-title>
        </v-row>
        <v-content>
          <v-card max-width="600" class="mx-auto" color="ffffff">
            <v-container fluid>
              <v-col cols="10">
                <v-text-field
                  outlined
                  label="ID ผู้ใช้งาน"
                  item-value="member_id"
                  v-model="memberId"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-text-field>
                <p v-if="CheckID != ''">ชื่อผู้ใช้ : {{name}}</p>
              </v-col>
              <v-col cols="2">
                <div class="my-2">
                  <v-btn @click="ShowMemberId" depressed large color="primary">Search</v-btn>
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
                    prepend-icon="mdi-mouse"
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

                <!-- <v-col cols="12">
                  <v-select
                    v-model="employeeId"
                    :items="employees"
                    item-value="emp_id"
                    item-text="name"
                    label="เลือกพนักงาน"
                    color="blue"
                    prepend-icon="mdi-human"
                  ></v-select>
                </v-col>-->

                <v-row justify="center">
                  <v-col cols="25">
                    <v-text-field
                      solo
                      label="employeeId"
                      v-model="nameemp"
                      :rules="[(v) => !!v || 'This field is required']"
                      required
                      prepend-icon="mdi-account"
                      readonly
                    ></v-text-field>
                  </v-col>
                </v-row>
              </div>
            </v-container>
            <v-card-actions color="#0D47A1">
              <v-btn @click="saveBorrow" block color="black" dark>บันทึกการยืมอุปกรณ์</v-btn>
            </v-card-actions>
          </v-card>
        </v-content>
        <v-navigation-drawer v-model="right" fixed right temporary></v-navigation-drawer>

        <br />
        <v-col cols="3">
      <v-btn x-medium color="#6C7B8B" style="margin-left: 380%;" dark @click="back">Back</v-btn>
    </v-col>
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>
      </v-card>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
import http from "../http-common";

export default {
  watch: {
    categoryId: function() {
      this.getSportequipments();
    }
  },
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
      name: "",
      nameemp:localStorage.getItem('name'),
    };
  },
  methods: {
     back() {
      this.$router.push("/Employeemenu");
    },
    ShowMemberId() {
      http
        // .get("/check/" + this.returns.return_id)
        .get("/Members/" + this.memberId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.name = response.data.name;
            this.CheckID = response.status;
         alert("มีสมาชิกนี้");
          } else {
            this.genderId = "";
            alert("ไม่มีสมาชิกนี้");
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
        .get("/sportequipment/" + this.categoryId)
        .then(response => {
          this.sportequipments = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
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
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveBorrow() {
      http
        .post(
          "/borrow/" +
            this.memberId +
            "/" +
            this.categoryId +
            "/" +
            this.sportequipmentId +
            "/" +
            localStorage.getItem('emp_id')
        )
        .then(response => {
          console.log(response);
        alert("บันทึกสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("ยืมไม่สำเร็จ");
        });
    },
    refreshList() {
      this.getEmployees();
      //this.getSportequipments();
      this.getCategorys();
    }
  },
  mounted() {
    this.getEmployees();
    // this.getSportequipments();
    this.getCategorys();
  }
};
</script>