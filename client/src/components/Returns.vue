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
            <h1>คืนอุปกรณ์กีฬา</h1>
          </v-toolbar-title>
        </v-row>

        <v-row justify="center">
          <v-col cols="6">
            <v-row justify="center">
              <v-col cols="10">
                <v-text-field
                  outlined
                  label="ID ผู้ใช้งาน"
                  v-model="member_id"
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
            </v-row>

            <div v-if="CheckID">
              <v-row justify="center">
                <v-col cols="25">
                  <v-text-field
                    solo
                    label="พนักงาน"
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
                  <v-select
                    label="อุปกรณ์ที่คืน"
                    solo
                    v-model="returns.borrows_Id"
                    :items="borrows"
                    item-text="sportequipment.se_name"
                    item-value="borrow_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                    prepend-icon="mdi-football"
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify="center">
                <v-col cols="25">
                  <v-select
                    label="สภาพอุปกรณ์ที่คืน"
                    solo
                    v-model="returns.statusId"
                    :items="statuss"
                    item-text="statuss"
                    item-value="status_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                    prepend-icon="mdi-more"
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify="center">
                <v-col cols="12">
                  <v-btn style="margin-left" @click="clear">clear</v-btn>
                  <v-btn style="margin-left:55%" @click="saveData">คืนอุปกรณ์</v-btn>
                </v-col>
              </v-row>
              <v-col cols="3">
                <v-btn x-medium color="#6C7B8B" style="margin-left: 380%;" dark @click="back">Back</v-btn>
              </v-col>
            </div>
          </v-col>
        </v-row>
        <br />
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
    member_id: function() {
      this.getBorrows();
    }
  },
  data() {
    return {
      returns: {
        statusId: null,
        employee_Id: null,
        returns: null,
        borrows_Id: null
      },
      member_id: null,
      name: "",
      employee: null,
      borrows: [],
      statuss: null,
      CheckID: false,
      borrow_date: null,
      nameemp: localStorage.getItem("name")
    };
  },

  methods: {
    ShowMember_id() {
      http
        .get("/Members/" + this.member_id)
        .then(response => {
          console.log(JSON.parse(JSON.stringify(response.data)));
          if (response.data != null) {
            this.name = response.data.name;
            this.CheckID = response.status;
            alert("พบผู้ใช้งาน");
          } else {
            this.returns.genderId = "";
            alert("ไม่พบผู้ใช้งาน");
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
          this.employee = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getStatuss() {
      http
        .get("/status")
        .then(response => {
          this.statuss = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBorrows() {
      http
        .get("/borrow/" + this.member_id)
        .then(response => {
          this.borrows = response.data;
          console.log(JSON.parse(JSON.stringify(response.data)));
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveData() {
      http
        .post(
          "/Returns/" +
            localStorage.getItem("emp_id") +
            "/" +
            this.member_id +
            "/" +
            this.returns.statusId +
            "/" +
            this.returns.borrows_Id
        )
        .then(response => {
          console.log(response);
          alert("คืนสำเร็จ");
        })
        .catch(e => {
          console.log(e);
          alert("คืนไม่สำเร็จ");
        });
    },
    clear() {
      //this.returns.borrows_Id = "";
      this.returns.statusId = "";
    },
    back() {
      this.$router.push("/Employeemenu");
    }
  },
  mounted() {
    this.getStatuss();
    this.getEmployees();
    //this.getBorrow();
  }
};
</script>
