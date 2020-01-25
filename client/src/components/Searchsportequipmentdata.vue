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
              <h1 class="display-2 font-weight-bold mb-3">อุปกรณ์กีฬา</h1>
            </v-flex>
          </v-layout>
    <!-- ============================================================================      -->
 
 <v-row justify="center">
          <v-toolbar-title>
            <h4>ID สำหรับค้นหาอุปกรณ์ของแต่ละประเภท</h4>
          </v-toolbar-title>
        </v-row>

<v-row justify="center">
          <v-toolbar-title>
            <h5>1 = กีฬากลางแจ้ง</h5>
            <h5>2 = กีฬาทางน้ำ</h5>
            <h5>3 = กีฬาในร่ม</h5>
          </v-toolbar-title>
        </v-row>
 
 <v-row justify="center" >
        <v-col cols="4">
        <br />
      <v-text-field
        solo
        v-model="search"
        label="ประเภทอุปกรณ์กีฬา"
        single-line
        hide-details
      ></v-text-field>  
    </v-col>
    </v-row>
    <v-row justify='center'>
<v-btn color="blue" @click="Check">ค้นหา</v-btn>
 <v-snackbar v-model="alwayselect" :timeout="timeout" :vertical="vertical">
                            <div v-if="checkSave==true" class="py-3">ค้นหาสำเร็จ</div>
                            <div v-if="checkSave==false" class="py-3">ไม่พบประเภทกีฬา</div>
                            <v-btn color="red" text @click="alwayselect = false">
                                Close
                            </v-btn>
                        </v-snackbar>
</v-row>

<!-- ============================================================================      -->



          <v-row justify="center">
            <v-col cols="8">
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
      checkSave: false,
    alwayselect:false,
    search: '',           
        headers: [
         { text: "ลำดับ",sortable: false, value: "id" },
        { text: "พนักงาน",sortable: false, value: "employee.name" },
        { text: "ประเภทกีฬา",sortable: false, value: "category.category_name" },
        { text: "ชื่อกีฬา",sortable: false, value: "se_name" },
        { text: "ชนิดกีฬา",sortable: false, value: "sporttype.sport_type" },
        { text: "ยี่ห้อ",sortable: false, value: "brand" },
        { text: "ราคา",sortable: false, value: "price" },
        { text: "วันที่",sortable: false, value: "date" },
      ],
      items: []
    };
  },

  methods: {
    Check() {
      http
        .get("/sportequipment/"+this.search)
        .then(response => {
          this.items = response.data;
          if(this.items.length == 0){
          this.alwayselect = true;
            this.checkSave = false;
            }
          else {
          this.alwayselect = true;
            this.checkSave = true;
          }
            console.log(this.items);
          
        })
        .catch(e => {
          console.log(e);
          this.alwayselect = true;
            this.checkSave = true;
        });
    },
    back() {
      this.$router.push("/Employeemenu");
    }
  },

};
</script>