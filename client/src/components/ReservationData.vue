<template>
  <v-card class="mx-auto" height="100%" width="500%">
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >
      <br />
      <v-card class="mx-auto" max-width="1500" color="#8B636C">
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-container>
          <v-layout text-center wrap>
            <v-flex mb-4>
              <br />
              <h1 class="display-2 font-weight-bold mb-3">ข้อมูลการจองสถานกีฬา</h1>
            </v-flex>
          </v-layout>
         

          <v-row justify="center">
            <v-col cols="12">
              <v-data-table
                :headers="headers"
                :items="items"
                :items-per-page="5"
                class="elevation-1"
              ></v-data-table>
              <v-col cols="3">
                <v-btn x-medium color="#6C7B8B" style="margin-left: 400%;" dark @click="back">Back</v-btn>
              </v-col>
            </v-col>
          </v-row> 
          <div v-if="alrat1">
                <v-alert type="success">พบข้อมูลการจอง</v-alert>
              </div>
              <div v-else-if="!alert1">
                <v-alert type="error">ไม่พบข้อมูลการจอง</v-alert>
              </div>
        </v-container>

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
  data() {
    return {
      headers: [
        {
          text: "ลำดับ",
          align: "left",
          sortable: false,
          value: "reservation_id"
        },
        { text: "Username", value: "members.username" },
        { text: "ประเภทสนามกีฬา", value: "fieldtype.fieldtype_name" },
        { text: "ประเภทการเข้าใช้สนามกีฬา", value: "fielduse.fielduse_name" },
        { text: "พนักงาน", value: "employee.name" },
        { text: "วันที่จองสถานกีฬา", value: "date" },
        { text: "เวลาเข้าสถานกีฬา", value: "start_time" },
        { text: "เวลาออกสถานกีฬา", value: "end_time" },
      ],
      items: []
    };
  },

  methods: {
    getReservation() {
      http
        .get("/reservation")
        .then(response => {
          this.items = response.data;
          console.log(response.data);
         if (response.data != "") {
            this.items = response.data;
            this.alrat1 = true;
            console.log(response.data);
            console.log("++++++++++++++");
            console.log("true");
          } else {
            this.alrat1 = false;
            console.log("false");
          }
        })
        .catch(e => {
          console.log(e);
          this.alert = "false";

        });
    },
    back() {
      this.$router.push("/Employeemenu");
    }
  },

  mounted() {
    this.getReservation();
  }
};
</script>