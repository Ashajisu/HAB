<template>
  <h1>월별 지출내역</h1>
  <hr>
  <div class="xlxsInput">
    <label>
      파일 입력 : 
      <input type="file" onchange="readExcel()">
    </label>
  </div>
  <table class="out">
    <thead>
        <th>연번</th>
        <th>일자</th>
        <th>차변분류</th>
        <th>차변금액</th>
        <th>대변분류</th>
        <th>대변금액</th>
        <th>비고</th>
    </thead>
    <tbody id='tableBody'>
    </tbody>
    <thead>
      <tr>
        <th colspan='2'>합계</th>
        <th colspan='2' id='sumL'>-</th>
        <th colspan='2' id='sumR'>-</th>
        <th id="check">대차금액</th>
      </tr>
    </thead>
  </table>
</template>

<script>
// import * as xlsx from 'xlsx'

export default {
  name: "MonthList",
  components: {
    // xlsx
  },
  data() { //변수생성
    return {}
  },
  watch: {},
  methods: {
    // makeExcelFile5() {
    //   const wb = xlsx.utils.book_new();

    //   const data = this.selectedUsers.map(user => ({
    //     청구년월: this.today(),
    //     청구항목: user.empId,
    //     청구금액: user.empRank,
    //     납부자명: "(주)SSD파트너",
    //     납부자구분: "급여",
    //     납부전용계좌: user.empRank
    //   }));

    //   const ws = xlsx.utils.json_to_sheet(data);
    //   xlsx.utils.book_append_sheet(wb, ws, "Sheet1");
    //   xlsx.writeFile(wb, "지출내역서.xlsx");
    // },
  },
  computed:{},
  mounted() {}
}
</script>
<style>
  h1{
    text-align: left;
    margin-left: 100px;
  }
  div.xlxsInput{
    text-align: right;
    margin-right: 100px;
  }
	table.out {
		margin: auto;
		border-spacing: 0px;
		margin-top: 20px;
		width: 1000px;
		text-align: center;
	}
  table.out td:nth-child(4), td:nth-child(6){
    text-align: left;
  }
  table.out td:nth-child(2), td:nth-child(5){
    min-width: 80px;
  }

  table.out td:nth-child(7){
    min-width: 180px;
  }
  table.out td:nth-child(1){
    min-width: 40px;
  }

	table.out thead{
		background-color: rgb(34, 176, 181);
		padding: 5px 20px 5px 20px;
	}

	table.out th,td{
		border: 0px;
		padding: 5px 5px 5px 5px;
	}

	table.out tr:nth-child(odd) {
		background-color: rgba(1, 170, 176, 0.3);
	}
	table.out tr:nth-child(even) {
		background-color: rgba(29, 211, 217, 0.1);
	}

</style>