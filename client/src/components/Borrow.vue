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
                  label="User Name"
                  v-model="member_username"
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
              <div v-if="alert1 == 'null'"></div>
              <div v-else-if="alert1 == 'true'">
                <v-alert type="success">พบผู้ใช้งาน</v-alert>
              </div>
              <div v-else-if="alert1 == 'false'">
                <v-alert type="error">ไม่พบผู้ใช้งาน</v-alert>
              </div>

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
                <v-row justify="center">
                <v-col cols="25">
                  <v-text-field
                    label="note"
                    solo
                    v-model="note"
                    prepend-icon="mdi-book"
                    :rules="[(v) => !!v || 'This field is required']"
                    required
                    clearable
                  ></v-text-field>
                </v-col>
                </v-row>

                <div v-if="alert === 'null'"></div>
                <div v-else-if="alert === 'true'">
                  <v-alert type="success">บันทึกสำเร็จ</v-alert>
                </div>
                <div v-else-if="alert === 'false'">
                  <v-alert type="error">ยืมไม่สำเร็จ</v-alert>
                </div>
              </div>
            </v-container>
            <v-card-actions color="#0D47A1">
              <v-btn @click="saveBorrow" block color="black" dark>ยืมอุปกรณ์กีฬา</v-btn>
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
      alert: "null",
      alert1: "null",
      member_username: null,
      member_id: null,
      member_id1: null,
      sportequipmentId: null,
      categoryId: null,
      employeeId: null,
      right: undefined,
      sportequipments: [],
      employees: null,
      note: null,
      categorys: [],
      CheckID: false,
      name: "",
      nameemp: localStorage.getItem("name")
    };
  },
  methods: {
    back() {
      this.$router.push("/Employeemenu");
    },
    ShowMemberId() {
      http
        .get("/Members/" + this.member_username)
        .then(response => {
          console.log(response);
          // console.log(JSON.parse(JSON.stringify(response.data)));
          if (response.data != []) {
            this.member_id1 = response.data.member_id;
            this.name = response.data.name;
            this.CheckID = response.status;
            this.alert1 = "true";
            // alert("พบผู้ใช้งาน");
          } else {
            this.member_id1 = null;
            this.alert1 = "false";
            // alert("ไม่พบผู้ใช้งาน");
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
            this.member_id1 +
            "/" +
            this.categoryId +
            "/" +
            this.sportequipmentId +
            "/" +
            localStorage.getItem("emp_id") +
            "/" +
            this.note
        )
        .then(response => {
          console.log(response);
          this.alert = "true";
        })
        .catch(e => {
          console.log(e);
          this.alert = "false";
        });
    },
    refreshList() {
      this.getEmployees();
      this.getCategorys();
    }
  },
  mounted() {
    this.getEmployees();
    this.getCategorys();
  }
};
</script>