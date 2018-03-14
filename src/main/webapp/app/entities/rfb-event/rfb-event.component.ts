import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { RfbEvent } from './rfb-event.model';
import { RfbEventService } from './rfb-event.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-rfb-event',
    templateUrl: './rfb-event.component.html'
})
export class RfbEventComponent implements OnInit, OnDestroy {
rfbEvents: RfbEvent[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private rfbEventService: RfbEventService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.rfbEventService.query().subscribe(
            (res: HttpResponse<RfbEvent[]>) => {
                this.rfbEvents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInRfbEvents();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: RfbEvent) {
        return item.id;
    }
    registerChangeInRfbEvents() {
        this.eventSubscriber = this.eventManager.subscribe('rfbEventListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
