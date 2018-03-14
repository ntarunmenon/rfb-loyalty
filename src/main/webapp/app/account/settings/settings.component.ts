import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { Principal, AccountService } from '../../shared';
import {RfbLocation} from '../../entities/rfb-location/rfb-location.model';
import {RfbLocationService} from '../../entities/rfb-location/rfb-location.service';

@Component({
    selector: 'jhi-settings',
    templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
    error: string;
    success: string;
    settingsAccount: any;
    languages: any[];
    locations: RfbLocation[];

    constructor(
        private account: AccountService,
        private principal: Principal,
        private locationService: RfbLocationService,
        private jhiAlertService: JhiAlertService
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
           console.log('inside settings');
           console.log(account);
            this.settingsAccount = this.copyAccount(account);
        });
        this.loadLocations();
    }

    save() {
        this.account.save(this.settingsAccount).subscribe(() => {
            this.error = null;
            this.success = 'OK';
            this.principal.identity(true).then((account) => {
                this.settingsAccount = this.copyAccount(account);
            });
        }, () => {
            this.success = null;
            this.error = 'ERROR';
        });
    }

    copyAccount(account) {
        return {
            activated: account.activated,
            email: account.email,
            firstName: account.firstName,
            langKey: account.langKey,
            lastName: account.lastName,
            login: account.login,
            imageUrl: account.imageUrl,
            homeLocation: account.homeLocation
        };
    }

    loadLocations(): any {
        this.locations = [];
        this.locationService.query().subscribe(
            (res: HttpResponse<RfbLocation[]>) => {
                console.log('Loaded  locations');
                this.locations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
