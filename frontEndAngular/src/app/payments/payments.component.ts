import {Component, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrl: './payments.component.css'
})
export class PaymentsComponent implements OnInit{
  public payments: any ;
  public datasource: any;
  public displayedColumns: string[] = ['id', 'date', 'amount', 'type', 'status'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(private http: HttpClient
  ) {
  }
  ngOnInit(): void {
    this.http.get('http://localhost:8089/payments').subscribe({
      next: (data) => {
        this.payments = data;
        this.datasource = new MatTableDataSource(this.payments)
        this.datasource.paginator = this.paginator;
        this.datasource.sort = this.sort
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

}
