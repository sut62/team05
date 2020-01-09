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
                  v-model="returns.member_id"
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
                  <v-select
                    label="พนักงานรับคืน"
                    solo
                    v-model="returns.employee_Id"
                    :items="employee"
                    item-text=".name"
                    item-value="emp_id"
                    :rules="[(v) => !!v || 'Item is required']"
                    required
                    prepend-icon="mdi-account"
                  ></v-select>
                </v-col>
              </v-row>

              <v-row justify="center">
                <v-col cols="25">
                  <v-select
                    label="อุปกรณ์ที่คืน"
                    solo
                    v-model="returns.borrows_Id"
                    :items="borrow"
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
                  <v-btn style="margin-left: 5%;" @click="clear">clear</v-btn>
                  <v-btn style="margin-left: 55%;" @click="saveData">คืนอุปกรณ์</v-btn>
                </v-col>
              </v-row>

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
  // watch: {
  //   memberId: function(val) {
  //     this.getBorrow();
  //   }
  // },
  data() {
    return {
      returns: {
        statusId: null,
        employee_Id: null,
        returns: null,
        borrows_Id: null,
        member_id: null
      },
      name: "",
      employee: null,
      statuss: null,
      CheckID: false,
      borrow_date: null
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
    getBorrow() {
      http
        .get("/borrow")
        .then(response => {
          this.borrow = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // getBorrows() {
    //   http
    //     .get("/borrow/Members/" + this.returns.member_id)
    //     .then(response => {
    //       this.borrow = response.data;
    //       console.log("borrows = ");
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    saveData() {
      http
        .post(
          "/Returns/" +
            this.returns.employee_Id +
            "/" +
            this.returns.member_id +
            "/" +
            this.returns.statusId +
            "/" +
            this.returns.borrows_Id
        )
        .then(response => {
          console.log(response);
          alert("คืนสำเร็จ");
          // this.$router.push("/registeruser/login");
        })
        .catch(e => {
          console.log(e);
          alert("คืนไม่สำเร็จ");
        });
    },
    clear(){
      this.returns.borrows_Id = '';
      this.returns.statusId = '';
    
    }
  },
  mounted() {
    this.getStatuss();
    this.getEmployees();
    this.getBorrow();
  }
};
</script>
