import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LogInView } from './view/logIn/logInView';

import {AccountListView} from "./view/accountList/accountListView";
import {AccountDetailView} from "./view/accountDetail/accountDetailView";
import {CandidateListView} from "./view/candidateList/candidateListView";
import {CandidateDetailView} from "./view/candidateDetail/candidateDetailView";
import { HomePageView } from './view/homePage/homePageView';
import {TestComponent} from "./view/test/test.component";


const routes: Routes = [

  // For Log In, without Login
  {path:"login"                            , component:LogInView               },

  // HomePage Dashboard
  {path:":role/homepage"                   , component:HomePageView            }, // TODO

  // For Admin, SuperAdmin
  {path:":role/manage/User"                , component:AccountListView         }, // TODO
  {path:":role/check/User/Detail/:id"      , component:AccountDetailView       }, // TODO

  // For All Account
  {path:":role/manage/Candidate"           , component:CandidateListView       }, // TODO
  {path:":role/check/Candidate/Detail/:id" , component:CandidateDetailView     }, // TODO

  // For Test
  {path:"test"                              , component:TestComponent          }, // TODO

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule
{
}
