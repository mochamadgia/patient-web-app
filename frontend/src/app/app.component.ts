import {Component, OnInit} from '@angular/core';
import {ChildrenOutletContexts} from "@angular/router";
import {initFlowbite} from "flowbite";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private contexts: ChildrenOutletContexts) {}


  ngOnInit(): void {
    initFlowbite();
  }
}
