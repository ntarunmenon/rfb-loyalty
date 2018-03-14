import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RfbLocation } from './rfb-location.model';
import { RfbLocationService } from './rfb-location.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-rfb-location',
    templateUrl: './rfb-location.component.html'
})
export class RfbLocationComponent implements OnInit, OnDestroy {
rfbLocations: RfbLocation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private rfbLocationService: RfbLocationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.rfbLocationService.query().subscribe(
            (res: HttpResponse<RfbLocation[]>) => {
                this.rfbLocations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRfbLocations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RfbLocation) {
        return item.id;
    }
    registerChangeInRfbLocations() {
        this.eventSubscriber = this.eventManager.subscribe('rfbLocationListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
