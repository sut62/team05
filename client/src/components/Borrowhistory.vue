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
      <v-card class="mx-auto" max-width="1500" color="#8B636C">
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-system-bar color="#CD919E"></v-system-bar>
        <v-container>
          <v-layout text-center wrap>
            <v-flex mb-4>
              <br />
              <h1 class="display-2 font-weight-bold mb-3">ประวัติการยืมอุปกรณ์กีฬา</h1>
            </v-flex>
          </v-layout>

          <v-row justify="center">
            <v-col cols="8">
              <v-data-table
                :headers="headers"
                :items="items"
                :items-per-page="5"
                class="elevation-1"
              ></v-data-table>
              <div v-if="alrat1">
                <v-alert type="success">พบข้อมูลประวัติการยืม</v-alert>
              </div>
              <div v-else-if="!alert1">
                <v-alert type="error">ไม่พบข้อมูลประวัติการยืม</v-alert>
              </div>
              <v-col cols="3">
                <v-btn x-medium color="#6C7B8B" style="margin-left: 400%;" dark @click="back">Back</v-btn>
              </v-col>
            </v-col>
          </v-row>
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
          value: "borrow_id"
        },
        { text: "Username", value: "members.username" },
        { text: "ประเภทอุปกรณ์กีฬา", value: "category.category_name" },
        { text: "อุปกรณ์กีฬา", value: "sportequipment.se_name" },
        { text: "พนักงาน", value: "employee.name" },
        { text: "เวลาที่ยืม", value: "borrow_date" },
         { text: "Note", value: "note" }
      ],
      items: []
    };
  },

  methods: {
    getBorrow() {
      http
        .get("/borrow")
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
    this.getBorrow();
  }
};
</script>