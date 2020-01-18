
<template>
  <v-card
    class="mx-auto"
    height="100%"
    width="100%"
  >
    <v-navigation-drawer
      absolute
      dark
      src="https://c.wallhere.com/photos/e4/53/1920x1080_px_anime_Der_Wanderer_ber_Dem_Nebelmeer_nature_sky_sunset-609196.jpg!d"
      width="100%"
      permanent
    >

    <br>
<v-card
    class="mx-auto"
    max-width="850"
    color = "#8B636C"
  >
  
<v-system-bar color=#CD919E></v-system-bar>
<v-system-bar color=#CD919E></v-system-bar>

 <v-row justify="center">
<v-toolbar-title ><h1>ลงทะเบียนอุปกรณ์กีฬา</h1></v-toolbar-title>

</v-row>
<v-row justify="center">
  
                <v-col cols="5">
                  <v-text-field
                solo
                label="=ชื่อพนักงาน"
                v-model="nameemp"
                :rules="[(v) => !!v || 'This field is required']"
                required
                prepend-icon="mdi-account"
                readonly
              ></v-text-field>
                </v-col>
              
</v-row>
<v-row justify="center">
  <v-col cols="6" sm="6" md="5" >
                <v-select
                 label="ประเภทกีฬา"
                  solo
                  v-model="Sportequipment.categoryId"
                  :items="categorys"
                  item-text="category_name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-select>
        </v-col>

<v-col cols="4">
                <v-text-field
                  label="ชื่ออุปกรณ์"
                  solo
                   v-model= "se_name"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable
                ></v-text-field>
              </v-col>
 </v-row>

<v-row justify="center">
   <v-col cols="12" sm="6" md="5" >
          <v-select
            label="ชนิดกีฬา"
            solo
              v-model="Sportequipment.sporttypeId"
                  :items="sporttypes"
                  item-text="sport_type"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
          ></v-select>
        </v-col>
</v-row>
<v-row justify="center">
         <v-col cols="3">
                <v-text-field
                  label="ยีห้อ"
                  solo
                  v-model= "brand"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable
              ></v-text-field>
              </v-col>

         <v-col cols="3">
                <v-text-field
                  label="ราคา"
                  solo
                   v-model= "price"
                :rules="[(v) => !!v || 'This field is required']"
                required
                clearable
              ></v-text-field>
              </v-col>
</v-row>
<v-row justify="center">
           <v-col cols="12" sm="6" md="4">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="วันที่ซื้อ"
            prepend-icon="mdi-calendar"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
    
            </v-row>
             <v-row justify="center">
            <v-col cols="12">
              <v-btn 
                style="margin-left: 25%;"
                @click="clear">clear
              </v-btn>
              <v-btn 
                style="margin-left: 30%;"
                @click="saveData">save
              </v-btn>
            </v-col>
          </v-row>
          <br>
          <v-col cols="3">
      <v-btn x-medium color="#6C7B8B" style="margin-left: 380%;" dark @click="back">Back</v-btn>
    </v-col>


<v-system-bar color=#CD919E></v-system-bar>
<v-system-bar color=#CD919E></v-system-bar>
</v-card>
</v-navigation-drawer>
</v-card>

</template>

<script>
import http from "../http-common";
export default {
  
  name: "Sportequipment",
  data() {
    return {
      Sportequipment: {
        employeeId: null,
        categoryId: null,
        sporttypeId: null,
      },
      brand: "",
      price: "",
      date: "",
      se_name: "",
      categorys: null,
      employees: null,
      sporttypes: null,
      nameemp: localStorage.getItem('name'),
    };
  },

  methods: {
   
    getCategory() {
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
    getEmployee() {
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
    getSporttype() {
      http
        .get("/sporttype")
        .then(response => {
          this.sporttypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

     // function เมื่อกดปุ่ม submit
   saveData() {
      http
        .post(
          "/Sportequipment/" + localStorage.getItem('emp_id') +
          "/" +  this.Sportequipment.categoryId + 
           "/" + this.se_name + 
           "/" + this.Sportequipment.sporttypeId +
           "/" + this.brand + 
           "/" + this.price+
            "/" + this.date  ,

            this.Sportequipment
          
        )
    .then(response => {
        
          console.log(response);
          alert("บันทึกสำเร็จ");  
        })
    .catch(e => {
          console.log(e);
          alert("บันทึกไม่สำเร็จ");  
        });  
    
  },
  back() {
      this.$router.push("/Employeemenu");
    },
    clear() {
     
        this.se_name = '';
        this.date = '';
        this.brand = '';
        this.price = '';
       
        this.Sportequipment.sporttypes = null;
        this.Sportequipment.employees = null;
        this.Sportequipment.categorys = null;
    },

  },
    mounted() {
    
      this.getSporttype();
      this.getEmployee();
      this.getCategory();
      
  }
};
</script>

