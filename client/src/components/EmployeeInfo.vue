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
              <h1 class="display-2 font-weight-bold mb-3">ข้อมูลพนักงาน</h1>
            </v-flex>
          </v-layout>

          <v-row justify="center">
            <v-col cols="15">
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
          value: "emp_id"
        },
        { text: "ชื่อ-สกุล", value: "name" },
        { text: "ตำแหน่ง", value: "position.position" },
        { text: "จังหวัด", value: "province.province" },
        { text: "ประเภทโทรศัพท์", value: "phonetype.phonetype" },
        { text: "เบอร์โทรศัพท์", value: "phonenumber" },
        { text: "Email", value: "email" },
        { text: "วันที่สมัคร", value: "timeRegis" }
      ],
      items: []
    };
  },

  methods: {
    getEmployee() {
      http
        .get("/Employee")
        .then(response => {
          this.items = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    back() {
      this.$router.push("/Adminmenu");
    }
  },

  mounted() {
    this.getEmployee();
  }
};
</script>