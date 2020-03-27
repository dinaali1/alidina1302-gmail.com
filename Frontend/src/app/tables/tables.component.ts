import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PromotionsService } from 'app/services/promotions.service';
import { NotificationService } from 'app/services/notification.service';

declare interface TableData {
    headerRow: string[];
    dataRows: string[][];
}

@Component({
    selector: 'app-tables',
    templateUrl: './tables.component.html',
    styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {

    public searchForm: FormGroup;
    public tableData1: TableData;
    public tableData2: TableData;

    constructor(
        private formBuilder: FormBuilder,
        private promotionService: PromotionsService,
        private notificationService: NotificationService,
    ) {
        this.searchForm = this.formBuilder.group({
            grade: ['', Validators.required],
            year: ['', Validators.required],
        });
    }

    ngOnInit() {
        this.tableData1 = {
            headerRow: ['ID', 'Name', 'email', 'grade', 'year', 'result'],
            dataRows: [],
        };
    }

    search() {
        const filters = this.searchForm.value;
        this.promotionService.getPromotionByYearAndGrade(filters).subscribe(
            students => {
                console.log(students);
                this.tableData1.dataRows = students.map(element => [
                    element.student.id,
                    element.student.firstName + ' ' + element.student.lastName,
                    element.student.email,
                    element.grade,
                    element.year,
                ])
            },
            err => console.log(err)
        )
    }

}
